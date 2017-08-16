package com.chengzhi.config;

import com.chengzhi.web.interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by 阮承志 on 2017/8/15.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.chengzhi.web")
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Resource
    private WebInterceptor webInterceptor;
    /**
     * addResourceHandler文件访问目录
     * addResourceLocations对外暴露的访问路径 classpath
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("").addResourceLocations();
    }

    /**
     * 添加自定义的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor);
    }
}
