package com.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.boot.demo.mapper")
public class UCreditApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UCreditApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UCreditApplication.class);
	}
}
