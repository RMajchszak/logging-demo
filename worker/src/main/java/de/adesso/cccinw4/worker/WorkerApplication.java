package de.adesso.cccinw4.worker;

import de.adesso.cccinw4.support.LogProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class WorkerApplication {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		LogProperties.setLogProperties("worker",null);
		SpringApplication.run(WorkerApplication.class, args);
	}


}

