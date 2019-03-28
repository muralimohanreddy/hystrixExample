package com.cgi.hystrixdashboard.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RequestFallbackHandler {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod= "hystrixFallback")
	public String hystrixHandleRequest() {
		
		return restTemplate.exchange("http://localhost:9090/getAllCustomer", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}).getBody();
	}
	
	public String hystrixFallback() {
		
		return restTemplate.exchange("http://localhost:9191/getAllCustomer", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}).getBody();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
