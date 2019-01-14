package de.adesso.cccinw4.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Applikation für den worker Microscervice
 */
@SpringBootApplication
public class WorkerApplication {

	/**
	 * Startet den worker Microservice
	 * @param args Standard Spring Boot Parameter
	 */
	public static void main(String[] args)  {
		SpringApplication.run(WorkerApplication.class, args);
	}


}

