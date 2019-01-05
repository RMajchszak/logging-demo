package de.adesso.cccinw4.primary;

import de.adesso.cccinw4.support.LogProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PrimaryApplication {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		LogProperties.setLogProperties("primary",null);
		SpringApplication.run(PrimaryApplication.class, args);
	}

}

