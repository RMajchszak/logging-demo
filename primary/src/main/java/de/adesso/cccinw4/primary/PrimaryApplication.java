package de.adesso.cccinw4.primary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring Boot Applikation für den Primary Microservice
 */
@SpringBootApplication
@EnableFeignClients
public class PrimaryApplication {

	/**
	 * Startet den Service
	 * @param args Spring Boot Standart-Parameter
	 */
	public static void main(String[] args) {
		SpringApplication.run(PrimaryApplication.class, args);
	}

}

