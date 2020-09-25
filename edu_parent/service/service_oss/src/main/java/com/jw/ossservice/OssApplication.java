package com.jw.ossservice;

import org.joda.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName OssApplication
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/9 13:59
 * @Version 1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//exclude = DataSourceAutoConfiguration.class不加载数据库配置
@ComponentScan(basePackages = {"com.jw"})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
