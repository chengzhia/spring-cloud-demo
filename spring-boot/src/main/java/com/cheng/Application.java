package com.cheng;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cheng.web.filter.XeFilter;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
		XeFilter xeFilter = new XeFilter();
		registrationBean.setFilter(xeFilter);
		List<String> urlPatternsList = new ArrayList<String>();
		urlPatternsList.add("/*");
		registrationBean.setUrlPatterns(urlPatternsList);
		return registrationBean;
	}
}
