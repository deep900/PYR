/**
 * 
 */
package com.pradheep.web.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.pradheep.dao.config.AppUserDetailService;
import com.pradheep.web.common.ApplicationLoggerWeb;
import com.pry.security.utility.SecurityConfiguration;

/**
 * @author pradheep.p
 * 
 */
@Configuration
@EnableWebMvcSecurity
@Order(99)
@Import({SecurityConfiguration.class})
public class AuthenticationSpringConfiguration extends WebSecurityConfigurerAdapter {

	private static Logger logger = null;

	static {
		System.out.println("Loading Authentication Spring configuration....");
	}

	@Autowired
	@Qualifier("accessDeniedHandler")
	AccessDeniedHandlerImpl accessDeniedHandlerImpl;

	private Logger getLogger() {
		if (logger == null) {
			logger = ApplicationLoggerWeb.getLogBean(getClass());
		}
		return logger;
	}

	@Override
	public AuthenticationManager authenticationManager() {
		AppAuthenticationManager appAuthenticationManager = new AppAuthenticationManager(getAuthenticationProvider());
		return appAuthenticationManager;
	}

	@Bean(name = "userDetailService")
	public AppUserDetailService getAppUserDetailService() {
		AppUserDetailService appUserDetailService = new AppUserDetailService();
		return appUserDetailService;
	}

	@Bean(name = "accessDeniedHandler")
	public MyAccessDeniedHandler getAccessDeniedHandler() {
		MyAccessDeniedHandler accessDeniedHandler = new MyAccessDeniedHandler();
		return accessDeniedHandler;
	}

	@Bean(name = "loginSuccessHandler")
	public SuccessHandler getLoginSuccessHandler() {
		SuccessHandler successHandler = new SuccessHandler();
		return successHandler;
	}

	public FailureHandler getAuthenticationFailureHandler() {
		FailureHandler failureHandler = new FailureHandler();
		return failureHandler;
	}

	@Bean(name = "authenticationProvider")
	public DaoAuthenticationProvider getAuthenticationProvider() {
		MyAuthenticationManager authenticationManager = new MyAuthenticationManager();
		authenticationManager.setUserDetailsService(getAppUserDetailService());
		authenticationManager.setForcePrincipalAsString(true);
		return authenticationManager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		getLogger().info("Loading security config " + new Date().toString());
		http.httpBasic();
		http.csrf().disable();
		http.authenticationProvider(getAuthenticationProvider());
		// -- Form login --//
		http.formLogin().successHandler(getLoginSuccessHandler());
		http.formLogin().loginProcessingUrl("/j_spring_security_check");
		http.formLogin().loginPage("/login-page");
		http.formLogin().failureUrl("/login-page?error=333");		
		// - URL pattern filter --//
		// http.authorizeRequests().antMatchers("/*").access("hasRole('ROLE_ANONYMOUS')");
		http.requiresChannel().anyRequest().requiresInsecure();
		//http.requiresChannel().antMatchers("/css*").requires("any");
		//http.requiresChannel().antMatchers("/js*").requires("any");
		http.requiresChannel().antMatchers("/admin/*").requiresSecure();
		http.requiresChannel().antMatchers("/login-page").requiresSecure();
		http.authorizeRequests().antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/admin/adminHome").access("!hasRole('ROLE_FIRST_TIME_LOGIN')");

		// ---- Logout -----//
		http.logout().invalidateHttpSession(true);
		http.logout().deleteCookies("JSESSIONID");
		http.logout().logoutUrl("/j_spring_security_logout");
		http.logout().logoutSuccessUrl("/logoutSuccess");
		
		// --- Session Management --//
		//http.sessionManagement().invalidSessionUrl("/invalidSession");
		http.sessionManagement().sessionFixation().migrateSession();
		http.sessionManagement().maximumSessions(1);
		http.exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
		getLogger().info("--------End of security config----------------");
	}

	@Bean(name = "sessionRegistry")
	public SessionRegistry getSessionRegistry() {
		SessionRegistry sessionRegistry = new org.springframework.security.core.session.SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean(name = "concurrentSessionAuthStratergyistry")
	public ConcurrentSessionControlAuthenticationStrategy getConcurrentSessionAuthStratergy() {
		ConcurrentSessionControlAuthenticationStrategy stratergy = new ConcurrentSessionControlAuthenticationStrategy(
				getSessionRegistry());
		stratergy.setMaximumSessions(1);
		stratergy.setExceptionIfMaximumExceeded(true);
		return stratergy;
	}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.authenticationProvider(getAuthenticationProvider()); logger.info(
	 * "Set the Authentication Provider " + auth.isConfigured()); }
	 */

	private Filter getFilter() {
		Filter f = new ConcurrentSessionFilter(getSessionRegistry(), "/sessionExpired");
		return f;
	}

	class AppAuthenticationManager implements AuthenticationManager {
		private AuthenticationProvider provider;

		public AppAuthenticationManager(AuthenticationProvider provider) {
			this.provider = provider;
		}

		public Authentication authenticate(Authentication arg0) throws AuthenticationException {
			return provider.authenticate(arg0);
		}

	}

	class MyAuthenticationManager extends DaoAuthenticationProvider {

		@Override
		public void setUserDetailsService(UserDetailsService userDetailsService) {
			super.setUserDetailsService(userDetailsService);
		}

		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			Authentication auth = null;
			try {
				getLogger().info("Authenticating the user details ");

				auth = super.authenticate(authentication);
			} catch (Exception err) {
				err.printStackTrace();
			}
			return auth;
		}

		@Override
		public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
			super.setForcePrincipalAsString(true);
		}
	}

	class SuccessHandler implements AuthenticationSuccessHandler {
		public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			System.out.println("Authentication Success ...");
			arg0.getSession().setAttribute("login-success", "true");
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			Iterator<String> iter = roles.iterator();
			while (iter.hasNext()) {
				System.out.println("Role :" + iter.next());
			}
			String loginId = authentication.getPrincipal().toString();
			if (roles.contains("ROLE_FIRST_TIME_LOGIN")) {
				response.sendRedirect("/admin/secureTokenPage?loginName=" + loginId);
			} else {
				response.sendRedirect(arg0.getContextPath() + "/admin/adminHome");
			}
		}
	}

	class MyTokenBasedFilter extends UsernamePasswordAuthenticationFilter {

		@Override
		public Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request,
				javax.servlet.http.HttpServletResponse response) throws AuthenticationException {
			getLogger().info("Inside My Token Based Filter");
			String _userName = request.getParameter("username").toString();
			String _password = request.getParameter("password").toString();
			String _grid_code = request.getParameter("grid_string").toString();
			String _grid_reference = request.getParameter("grid_reference").toString();
			System.out.println(_grid_code);
			System.out.println(_grid_reference);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(_userName,
					_password);
			List<String> gridDetails = new ArrayList<String>(2);
			gridDetails.add(0, _grid_code);
			gridDetails.add(1, _grid_reference);
			authentication.setDetails(gridDetails);
			authentication.setAuthenticated(false);
			Authentication auth = null;
			try {
				auth = getAuthenticationProvider().authenticate(authentication);				
			} catch (BadCredentialsException exp) {
				getLogger().error(exp.getMessage(), exp);
				auth.setAuthenticated(false);
				redirectFailure(request, response);
			} catch (Exception err) {
				getLogger().error("Unable to login", err);
				auth.setAuthenticated(false);
				redirectFailure(request, response);
			}
			redirectSuccessPostLogin(request,response);
			return auth;
		}

		public void redirectSuccessPostLogin(HttpServletRequest request, HttpServletResponse response) {
			try {
				response.sendRedirect(request.getContextPath() + "/admin/adminHome");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		public void redirectFailure(HttpServletRequest request, HttpServletResponse response) {
			try {
				response.sendRedirect(request.getContextPath() + "/login-page?error=333");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		public void redirectSuccess(HttpServletRequest request, HttpServletResponse response) {
			try {
				response.sendRedirect(request.getContextPath() + "/admin-home");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}

		@Override
		protected String obtainPassword(HttpServletRequest request) {
			String password = null;
			password = super.obtainPassword(request);
			return password;
		}

		@Override
		protected String obtainUsername(HttpServletRequest request) {
			String username = null;
			username = super.obtainUsername(request);
			return username;
		}
	}

	class FailureHandler implements AuthenticationFailureHandler {
		public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse response,
				AuthenticationException authentication) throws IOException, ServletException {
			System.out.println("Authentication failed ..");
			response.sendRedirect(arg0.getContextPath() + "/login-page?error=333");
		}
	}

}
