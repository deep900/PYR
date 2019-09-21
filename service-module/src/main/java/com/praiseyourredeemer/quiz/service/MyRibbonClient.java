package com.praiseyourredeemer.quiz.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
//@RibbonClient(name = "quiz-service", configuration = MyAppConfig.class)
public class MyRibbonClient {

	/*@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	
	public void callMe() {
		String greeting = this.restTemplate.getForObject("http://quiz-service/quiz-eng/5a6352de81b7f9717cb31de6", String.class);
		System.out.println(greeting);
	}

	public static void main(String[] args) {
		
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		callMe();
		System.out.println("End of client call..");
	}*/

}
