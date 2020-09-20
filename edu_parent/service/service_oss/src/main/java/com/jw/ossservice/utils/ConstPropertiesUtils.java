package com.jw.ossservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstPropertiesUtils
 * @Description 当项目启动spring 接口 spring加载之后执行接口的一个方法
 * @Author Jiang wei
 * @Date 2020/9/10 17:04
 * @Version 1.0
 **/
@Component
public class ConstPropertiesUtils implements InitializingBean {

    //读取配置文件内容
    @Value("${aliyun.oss.endpoint}")
    private  String endpoint;

    @Value("${aliyun.oss.keyid}")
    private  String keyid;

    @Value("${aliyun.oss.keysecret}")
    private  String keysecret;

    @Value("${aliyun.oss.bucketname}")
    private  String bucketname;

    //这个方法会被执行  在里面定义一些public 的static常量
    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT= endpoint;
        KEYID = keyid;
        KEYSECRET = keysecret;
        BUCKETNAME = bucketname;
    }
}
