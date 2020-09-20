package com.jw.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName EduConfig
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/7 3:57
 * @Version 1.0
 **/
@Configuration
@MapperScan("com.jw.eduservice.mapper")
public class EduConfig {

    /**
     * Mybatis-plus逻辑删除插件   以后版本不需要
     * */
    @Bean
    public ISqlInjector sqlInjector(){
        return  new LogicSqlInjector();
    }

    /**
     * mybatisplus分页插件
     * */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    /**
     * 乐观锁插件
     * */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {

        return new OptimisticLockerInterceptor();
    }

}
