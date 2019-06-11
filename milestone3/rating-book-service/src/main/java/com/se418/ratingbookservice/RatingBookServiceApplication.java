package com.se418.ratingbookservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
public class RatingBookServiceApplication {


//    @GetMapping("/")
//    public String handleRequest(@RequestParam long id, @RequestParam int rate) {
//
//		String serviceID = restTemplate.getForObject("http://book-service/", String.class);
//
//        return serviceID;
//    }

    public static void main(String[] args) {
        SpringApplication.run(RatingBookServiceApplication.class, args);
    }

}
