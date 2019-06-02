package com.se418.ratingbookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableHystrix
@EnableHystrxiDashboard
@RibbonClient(name="server", configuration = RibbonConfiguration.class)
public class RatingBookServiceApplication {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private FeignClient client;

	@LoadBalanced
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@GetMapping("/")
	public String getinstanceID() {
		return client.getinstanceID();
	}
	public static void main(String[] args) {
		SpringApplication.run(RatingBookServiceApplication.class, args);
	}


}
