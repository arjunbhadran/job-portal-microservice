package com.example.jobs.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @LoadBalanced //configures the restTemplate to use load balancer client under the hood
    @Bean //specifying it as a bean tells spring framework that it has to manage this objects of this class.
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
