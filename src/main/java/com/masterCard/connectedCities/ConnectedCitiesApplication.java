package com.masterCard.connectedCities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application to check if two cities are connected.
 *
 * @author Vijayakumar Gowda
 */
@SpringBootApplication
public class ConnectedCitiesApplication {

	/**
	 * The entry point of the application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConnectedCitiesApplication.class, args);
	}
}
