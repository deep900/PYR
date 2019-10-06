package com.pyr.templates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.pry.security.utility.SecurityConfiguration;
import com.pyr.messenger.Messenger;
import com.pyr.messenger.MessengerDelegate;
import com.pyr.messenger.NotificationLogger;
import com.pyr.messenger.PyrMessenger;
import com.pyr.messenger.SMSMessenger;

@Configuration
@Import({SecurityConfiguration.class})
public class NotificationConfiguration {

	@Bean(name = "emailMessenger")	
	public Messenger getEmailMessenger() {
		Messenger emailMessenger = new PyrMessenger();
		return emailMessenger;
	}

	@Bean(name = "smsMessenger")
	public Messenger getSMSMessenger() {
		Messenger smsMessenger = new SMSMessenger();
		return smsMessenger;
	}

	@Bean(name = "notificationDelegator")
	public MessengerDelegate getNotificationDelegate() {
		MessengerDelegate messengerDelegate = new MessengerDelegate();
		return messengerDelegate;

	}
	
	@Bean(name="notificationLogger")
	public NotificationLogger getLogger(){
		NotificationLogger notificationLogger = new NotificationLogger();
		return notificationLogger;
	}
	
	@Bean(name="threadPoolExecutor")
	public ThreadPoolTaskExecutor getThreadPoolExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(10);
		executor.setThreadNamePrefix("Notify-");
		return executor;
	}
}
