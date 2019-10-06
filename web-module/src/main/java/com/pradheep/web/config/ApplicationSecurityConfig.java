/**
 * 
 */
package com.pradheep.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author pradheep.p
 *
 */
/*@Configuration
@EnableWebSecurity*/
public class ApplicationSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll();
		http.requiresChannel().anyRequest().requiresInsecure();
	}*/
}
