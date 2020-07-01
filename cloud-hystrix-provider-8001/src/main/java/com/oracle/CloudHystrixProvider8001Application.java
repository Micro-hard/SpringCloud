package com.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//对hystrix熔断的支持
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
//用它一个顶三个
@SpringCloudApplication
public class CloudHystrixProvider8001Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixProvider8001Application.class, args);
    }
}
