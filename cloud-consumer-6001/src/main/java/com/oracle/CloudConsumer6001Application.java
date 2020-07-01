package com.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//启动eureka的服务
@EnableDiscoveryClient
@SpringBootApplication
public class CloudConsumer6001Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer6001Application.class, args);
    }

}
