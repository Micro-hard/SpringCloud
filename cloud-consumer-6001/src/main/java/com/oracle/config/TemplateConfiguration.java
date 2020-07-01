package com.oracle.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//自己定义的配置文件，  无状态的，这个的handler里需要RestTemplate
@Configuration
public class TemplateConfiguration {
    @Bean
    //0624 ribbon负载均衡的
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //0624  修改负载均衡的策略！！   默认是轮询
    @Bean
    public IRule randomRule(){
        return new RandomRule();//随机的
    }
}
