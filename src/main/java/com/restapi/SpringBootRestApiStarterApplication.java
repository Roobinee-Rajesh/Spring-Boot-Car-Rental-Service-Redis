package com.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@PropertySource("classpath:application.properties")
@EnableCaching
public class SpringBootRestApiStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiStarterApplication.class, args);
	}

}
