package com.jw.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName EduApplication
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/7 3:55
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.jw"})
@EnableDiscoveryClient  //开启nacos注册
@EnableFeignClients //开启远程调用服务
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
