/**
 * 
 */
package com.pry.security.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pradheep
 *
 */
@Configuration
public class SecurityConfiguration {

	@Bean
	public PublicUtility getPublicUtility(){
		PublicUtility utility = new PublicUtility();
		return utility;
	}
}
