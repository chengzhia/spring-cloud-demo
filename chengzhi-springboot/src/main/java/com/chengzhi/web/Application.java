package com.chengzhi.web;

import com.chengzhi.web.interceptor.WebInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
	public String returnStr () {
		return "HelloWorld";
	}
	@Bean
	public WebInterceptor webInterceptor() {
		return new WebInterceptor();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
