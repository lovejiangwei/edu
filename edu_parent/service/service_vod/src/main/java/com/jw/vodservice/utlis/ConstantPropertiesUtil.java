package com.jw.vodservice.utlis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantPropertiesUtil
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/21 11:11
 * @Version 1.0
 **/
@Component  //交给spring容器管理   spring自动配置为bean   @Bean注解是手动配置一个Bean
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.vod.keyid}")
    private String keyid;
    @Value("${aliyun.vod.keysecret}")
    private String keysecret;


    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    //spring 容器启动时会自动执行这个方法
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyid;
        ACCESS_KEY_SECRET = keysecret;
    }
}
