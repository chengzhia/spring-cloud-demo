package com.chengzhi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConfigServerClientApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServerClientApplication.class).web(true).run(args);
	}
}
