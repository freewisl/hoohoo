package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.example.controller", "com.example.manager", "com.example.security", "com.example.aop"})
@EntityScan({ "com.example.entity" })
@EnableJpaRepositories(basePackages = { "com.example.repository" })
public class HoohooApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HoohooApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HoohooApplication.class);
	}
}
