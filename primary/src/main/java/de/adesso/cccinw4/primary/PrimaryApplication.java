package de.adesso.cccinw4.primary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PrimaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimaryApplication.class, args);
	}

}

