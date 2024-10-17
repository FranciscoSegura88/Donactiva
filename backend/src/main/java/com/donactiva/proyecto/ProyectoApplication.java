package com.donactiva.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProyectoApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ProyectoApplication.class, args);
		
	}

}
