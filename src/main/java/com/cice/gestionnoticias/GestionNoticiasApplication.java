package com.cice.gestionnoticias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GestionNoticiasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionNoticiasApplication.class, args);
	}

}
