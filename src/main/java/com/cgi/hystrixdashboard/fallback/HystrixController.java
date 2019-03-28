package com.cgi.hystrixdashboard.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {
	
	@Autowired
	private RequestFallbackHandler rfh;
	
	@GetMapping("/getAllCustomer")
	public String getAllCustomers() {
		return rfh.hystrixHandleRequest();
	}

}
