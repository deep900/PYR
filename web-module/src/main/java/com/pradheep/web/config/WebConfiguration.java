/**
 * 
 */
package com.pradheep.web.config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.pradheep.dao.config.DAOConfig;
import com.pradheep.web.common.CommonTaskExecutor;
import com.pradheep.web.common.DailyQuizManager;
import com.pradheep.web.common.DailyVerseManager;
import com.pradheep.web.common.OneYearBible;
import com.pradheep.web.common.QuizHelper;
import com.pradheep.web.common.SubscriptionManager;
import com.pradheep.web.common.WebPageMonitorService;
import com.pradheep.web.common.event.EventManager;
import com.pradheep.web.controller.ApplicationLocaleResolver;
import com.pradheep.web.event.EventHandler;
import com.pradheep.web.event.PyrApplicationEventPublisher;
import com.pradheep.web.jobs.ApplicationScheduleService;
import com.pradheep.web.jobs.DBCleanupTask;
import com.pradheep.web.jobs.DailyEmailNotificationJob;
import com.pradheep.web.jobs.DailyQuizNotification;
import com.pradheep.web.jobs.DailySMSNotificationJob;
import com.pradheep.web.jobs.EventParticipantNotificationJob;
import com.pradheep.web.jobs.EventParticipantsReportJob;
import com.pradheep.web.jobs.MonthlyMessageNotification;
import com.pradheep.web.jobs.PersistedNotificationService;
import com.pradheep.web.service.EventManagementService;
import com.pyr.messenger.Messenger;
import com.pyr.messenger.PyrMessenger;
import com.pyr.messenger.SMSMessenger;
import com.pyr.templates.NotificationConfiguration;

/**
 * @author pradheep.p
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.pradheep.*" })
@EnableWebMvc
@Import({ DAOConfig.class, AuthenticationSpringConfiguration.class, NotificationConfiguration.class })
@EnableAspectJAutoProxy
public class WebConfiguration extends WebMvcConfigurerAdapter {

	static {
		System.out.println("Loading the web configuration...");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/home/praiseyourredeem/properties/**")
				.addResourceLocations("/home/praiseyourredeem/properties/");
		System.out.println("- - - Loading resources complete - - -");
	}

	@Bean(name = "localeChangeInterceptor")
	public LocaleChangeInterceptor getLocaleChangeInterceptor() {
		org.springframework.web.servlet.i18n.LocaleChangeInterceptor localeChangeInterceptor = new org.springframework.web.servlet.i18n.LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@PostConstruct
	public void doSomething() {
		System.out.println("Web Configuration is succussefully initialized..");
	}

	/*
	 * @Bean(name = "messageSource") public DBMessageSource
	 * getReloadableResourceBundleMessageSource() { DBMessageSource msgSource =
	 * new DBMessageSource(); return msgSource; }
	 */

	@Bean(name = "threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor getThreadPoolExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(5);
		taskExecutor.setCorePoolSize(3);
		taskExecutor.setThreadGroupName("PraiseYourRedeemerThreads");
		taskExecutor.setBeanName("threadPoolTaskExecutor");
		taskExecutor.setQueueCapacity(2000);
		taskExecutor.setDaemon(true);
		taskExecutor.setThreadNamePrefix("Pyr");
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return taskExecutor;
	}

	@Bean(name = "localeResolver")
	public org.springframework.web.servlet.i18n.SessionLocaleResolver getSessionLocaleResolver() {
		org.springframework.web.servlet.i18n.SessionLocaleResolver sessionLocaleResolver = new org.springframework.web.servlet.i18n.SessionLocaleResolver();
		Locale defaultLocale = new Locale.Builder().setLanguage("en").build();
		sessionLocaleResolver.setDefaultLocale(defaultLocale);
		return sessionLocaleResolver;
	}

	@Bean(name = "handlerMapping")
	public org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping getDefaultAnnotationHandlerMapping() {
		org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping defaultAnnotationHandlerMapping = new org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping();
		Object[] interceptor = new Object[1];
		interceptor[0] = getLocaleChangeInterceptor();
		defaultAnnotationHandlerMapping.setInterceptors(interceptor);
		return defaultAnnotationHandlerMapping;
	}

	@Bean(name = "applicationLocaleResolver")
	public ApplicationLocaleResolver getResponseConstants() {
		ApplicationLocaleResolver responseConstants = new ApplicationLocaleResolver();
		return responseConstants;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getLocaleChangeInterceptor());
	}

	/*
	 * @Bean(name = "messageSource") public DBMessageSource
	 * getReloadableResourceBundleMessageSource() { DBMessageSource msgSource =
	 * new DBMessageSource(); return msgSource; }
	 */

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		ReloadableResourceBundleMessageSource obj = new ReloadableResourceBundleMessageSource();
		obj.setBasename("classpath:messages");
		obj.setDefaultEncoding("UTF-8");
		obj.setUseCodeAsDefaultMessage(true);
		// System.out.println("Loaded all message bundle" +
		// obj.getMessage("mainpage.header", null, new
		// Locale("ta")));
		return obj;
	}

	@Bean(name = "manageHitAspect")
	public WebPageMonitorService getMonitorService() {
		WebPageMonitorService webPageMonitorService = new WebPageMonitorService();
		return webPageMonitorService;
	}

	@Bean(name = "dailyVerseManager")
	public DailyVerseManager getDailyVerseManager() {
		DailyVerseManager dailyVerseManager = new DailyVerseManager();
		return dailyVerseManager;
	}

	@Bean(name = "oneYearBibleManager")
	public OneYearBible getOneYearBibleManager() {
		OneYearBible oneYearBible = new OneYearBible();
		return oneYearBible;
	}

	@Bean(name = "applicationEventPublisher")
	public PyrApplicationEventPublisher getApplicationEventPublisher() {
		PyrApplicationEventPublisher eventPublisher = new PyrApplicationEventPublisher();
		return eventPublisher;
	}

	@Bean(name = "eventHandler")
	public EventHandler getEventHandler() {
		EventHandler eventHandler = new EventHandler();
		return eventHandler;
	}

	/**
	 * Handle the events asynchronously without affecting the existing
	 * listeners.
	 * 
	 * @return
	 */
	@Bean(name = "applicationEventMulticaster")
	public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
		SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

		eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
		return eventMulticaster;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultiPartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multiPartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multiPartResolver.setMaxUploadSize(100000l);
		return multiPartResolver;
	}

	@Bean(name = "multiPartInit")
	public MultiPartFileUploaderInitializer getInitializer() {
		MultiPartFileUploaderInitializer init = new MultiPartFileUploaderInitializer();
		return init;
	}

	@Bean(name = "commonTaskExecutor")
	public CommonTaskExecutor getCommonTaskExecutor() {
		CommonTaskExecutor commonTaskExecutor = new CommonTaskExecutor();
		return commonTaskExecutor;
	}

	@Bean(name = "smsNotificationService")
	public ApplicationScheduleService getSMSNotificationService() {
		ApplicationScheduleService smsNotificationService = new ApplicationScheduleService();
		return smsNotificationService;
	}

	@Bean(name = "threadPoolTaskScheduler")
	public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		threadPoolTaskScheduler.setThreadGroupName("Schedulers");
		return threadPoolTaskScheduler;
	}

	@Bean(name = "SMSNotificationJob")
	public DailySMSNotificationJob getDailySMSNotificationJob() {
		DailySMSNotificationJob dailySMSNotificationJob = new DailySMSNotificationJob();
		return dailySMSNotificationJob;
	}

	@Bean(name = "EmailNotificationJob")
	public DailyEmailNotificationJob getDailyEmailNotificationJob() {
		DailyEmailNotificationJob dailyEmailNotificationJob = new DailyEmailNotificationJob();
		return dailyEmailNotificationJob;
	}

	@Bean(name = "quizHelper")
	public QuizHelper getQuizHelper() {
		QuizHelper quizHelper = new QuizHelper();
		return quizHelper;
	}

	@Bean(name = "persistedNotificationService")
	public PersistedNotificationService getPersistedNotificationService() {
		PersistedNotificationService notificationService = new PersistedNotificationService();
		return notificationService;
	}

	@Bean(name = "monthlyMessageNotification")
	public MonthlyMessageNotification getMonthlyMessageNotification() {
		MonthlyMessageNotification monthlyMessageNotification = new MonthlyMessageNotification();
		return monthlyMessageNotification;
	}

	@Bean(name = "subscriptionManager")
	public SubscriptionManager subscriptionManager() {
		return new SubscriptionManager();
	}

	@Bean(name = "dailyQuizNotificationJob")
	public DailyQuizNotification getDailyQuizNotification() {
		return new DailyQuizNotification();
	}

	@Bean(name = "dailyQuizManager")
	public DailyQuizManager getDailyQuizManager() {
		return new DailyQuizManager();
	}

	@Bean(name = "dbCleanupJob")
	public DBCleanupTask getDBCleanupTask() {
		return new DBCleanupTask();
	}

	@Bean(name = "eventManager")
	public EventManager getEventManager() {
		return new EventManager();
	}

	@Bean(name = "eventManagementService")
	public EventManagementService getEventManagementService() {
		return new EventManagementService();
	}

	@Bean(name = "eventParticipantNotificationJob")
	public EventParticipantNotificationJob getEventParticipantNotificationJob() {
		return new EventParticipantNotificationJob();
	}

	@Bean(name = "eventParticipantsReportJob")
	public EventParticipantsReportJob getEventParticipantsReportJob() {
		return new EventParticipantsReportJob();
	}	
}
