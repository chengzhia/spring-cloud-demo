package com.chengzhi.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationService {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationService.class).web(true).run(args);
	}
}
