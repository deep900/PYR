package com.praiseyourredeemer.quiz.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.Applications;
import com.netflix.eventbus.impl.EventBusImpl;
import com.netflix.eventbus.spi.EventBus;

public class MicroserviceClient implements InitializingBean {
	public static final String REMOTE_REGION = "myregion";
	public static final String REMOTE_ZONE = "myzone";
	public static final int CLIENT_REFRESH_RATE = 10;
	public static final String EUREKA_TEST_NAMESPACE = "eureka.";
	private static final Set<String> SYSTEM_PROPERTY_TRACKER = new HashSet();
	private com.netflix.appinfo.ApplicationInfoManager applicationManager;
	private EventBus eventBus;
	
	public void discover1(){
		
        DiscoveryManager.getInstance().initComponent(new MyDataCenterInstanceConfig(), new DefaultEurekaClientConfig(EUREKA_TEST_NAMESPACE));
		//DiscoveryManager.getInstance().initComponent(new MyDataCenterInstanceConfig(), createEurekaClientConfig());
		/*InstanceInfo instance1 = DiscoveryManager.getInstance().getLookupService().getNextServerFromEureka(EUREKA_TEST_NAMESPACE, true);
		System.out.println(instance1);*/
		DiscoveryClient client = DiscoveryManager.getInstance().getDiscoveryClient();
		System.out.println(client);
		//String arg = client.getApplications().getNextIndex("localhost", false).toString();
		
		List<InstanceInfo> instanceInfoList = client.getInstancesByVipAddress("localhost", false);
		System.out.println(instanceInfoList);
	}

	public void discover() {
		AbstractConfiguration myAppConfig = setupDiscoveryClientConfig(1111,"/quiz-eng");
		DiscoveryManager.getInstance().initComponent(new MyDataCenterInstanceConfig(), createEurekaClientConfig());
		//DiscoveryManager.getInstance().initComponent(new MyDataCenterInstanceConfig(), myAppConfig);

		DiscoveryClient client = null;

		InstanceInfo nextServerInfo = null;
		try {
			// client = DiscoveryManager.getInstance().getDiscoveryClient();
			eventBus = new EventBusImpl();
			DiscoveryClientOptionalArgs optionalArgs = new DiscoveryClientOptionalArgs();

			client = new DiscoveryClient(getMyInstanceInfo(), createEurekaClientConfig(), optionalArgs);
			Applications app = client.getApplications();
			System.out.println("################## " + client.getInstanceRemoteStatus().name()); 
			
		} catch (Exception e) {
			System.err.println("Cannot get an instance of example service to talk to from eureka");
			e.printStackTrace();
		}
		System.out.println(client.getApplications().toString());
		System.out.println("Instances Size" + client.getInstancesByVipAddress("localhost", false).size());

		/*System.out.println("Found an instance of example service to talk to from eureka: "
				+ nextServerInfo.getVIPAddress() + ":" + nextServerInfo.getPort());

		System.out.println("override: " + nextServerInfo.getOverriddenStatus());

		System.out.println("Server Host Name " + nextServerInfo.getHostName() + " at port " + nextServerInfo.getPort());*/
	}

	public InstanceInfo getMyInstanceInfo() {
		return createApplicationManager().getInfo();
	}

	/*
	 * public void getQuiz() throws RestClientException, IOException {
	 * System.out.println("Inside method : getQuiz()"); List<ServiceInstance>
	 * instances = discoveryClient.getInstances("QUIZ-SERVICE"); ServiceInstance
	 * serviceInstance = instances.get(0); System.out.println(
	 * "Service Intance : " + serviceInstance.toString()); String baseUrl =
	 * serviceInstance.getUri().toString();
	 * 
	 * baseUrl = baseUrl + "/quiz-eng/5a618db281b7cd21776f3e4c"; URI x = null;
	 * try { x = new URI(baseUrl); } catch (URISyntaxException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); ResponseEntity<String>
	 * response = null; try { // response = restTemplate.exchange(baseUrl, //
	 * com.netflix.ribbon.proxy.annotation.Http.HttpMethod.GET, // getHeaders(),
	 * String.class,new String()); response = restTemplate.exchange(x,
	 * HttpMethod.GET, getHeaders(), String.class); } catch (Exception ex) {
	 * System.out.println(ex); } System.out.println(response.getBody()); }
	 */

	private ApplicationInfoManager createApplicationManager() {
		if (applicationManager == null) {
			EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig(EUREKA_TEST_NAMESPACE) {
				@Override
				public String getAppname() {
					return "QUIZ-SERVICE";
				}
				
				@Override
				public int getNonSecurePort(){
					return 1111;
				}

				@Override
				public int getLeaseRenewalIntervalInSeconds() {
					return 1;
				}
				
				@Override
				public String getHostName(boolean flag){
					return "localhost";
				}
			};
			ApplicationInfoManager.getInstance().initComponent(instanceConfig);
			applicationManager = ApplicationInfoManager.getInstance();
		}
		return applicationManager;
	}

	private EurekaClientConfig createEurekaClientConfig() {
		// Cluster connectivity
		/*
		 * String serviceURI;
		 * 
		 * serviceURI = "http://localhost:" + 1111;// + "/eureka/v2/";
		 * 
		 * bindProperty(EUREKA_TEST_NAMESPACE + "serviceUrl.default",
		 * serviceURI);
		 * 
		 * // Registration bindProperty(EUREKA_TEST_NAMESPACE +
		 * "registration.enabled", Boolean.toString(false));
		 * bindProperty(EUREKA_TEST_NAMESPACE +
		 * "appinfo.initial.replicate.time", Integer.toString(0));
		 * bindProperty(EUREKA_TEST_NAMESPACE + "appinfo.replicate.interval",
		 * Integer.toString(1));
		 * 
		 * // Registry fetch bindProperty(EUREKA_TEST_NAMESPACE +
		 * "shouldFetchRegistry", Boolean.toString(true));
		 * 
		 * bindProperty(EUREKA_TEST_NAMESPACE + "client.refresh.interval",
		 * Integer.toString(1));
		 */

		setupDiscoveryClientConfig(1111, "/quiz-eng");
		return new DefaultEurekaClientConfig(EUREKA_TEST_NAMESPACE);
	}

	private static void bindProperty(String propertyName, String value) {
		SYSTEM_PROPERTY_TRACKER.add(propertyName);
		ConfigurationManager.getConfigInstance().setProperty(propertyName, value);
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity(headers);
	}

	public void afterPropertiesSet() throws Exception {
		// getQuiz();
		discover();
	}
	
	
	
	/*public static void main(String args[]){
		MicroserviceClient client = new MicroserviceClient();
		client.discover1();
	}*/

	public static AbstractConfiguration setupDiscoveryClientConfig(int serverPort, String path) {
		AbstractConfiguration config = ConfigurationManager.getConfigInstance();
		config.setProperty("eureka.shouldFetchRegistry", "true");
		config.setProperty("eureka.responseCacheAutoExpirationInSeconds", "10");
		config.setProperty("eureka.client.refresh.interval", CLIENT_REFRESH_RATE);
		config.setProperty("eureka.registration.enabled", "false");
		config.setProperty("eureka.fetchRemoteRegionsRegistry", REMOTE_REGION);
		config.setProperty("eureka.myregion.availabilityZones", REMOTE_ZONE);
		config.setProperty("eureka.serviceUrl.default",
				"http://localhost:" + serverPort + path);
		return config;
	}

}
