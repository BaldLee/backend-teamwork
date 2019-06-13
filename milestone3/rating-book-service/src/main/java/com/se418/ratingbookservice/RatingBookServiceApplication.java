package com.se418.ratingbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "serverurl", configuration = RibbonConfiguration.class)
public class RatingBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingBookServiceApplication.class, args);
	}

}
