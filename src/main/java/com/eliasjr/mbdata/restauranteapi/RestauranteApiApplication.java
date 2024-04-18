package com.eliasjr.mbdata.restauranteapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestauranteApiApplication {

	private static final Logger logger = LogManager.getLogger(RestauranteApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestauranteApiApplication.class, args);
		logger.info("Aplicacaoo iniciada com sucesso!");
	}

}
