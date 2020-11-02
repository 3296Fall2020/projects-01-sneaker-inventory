package com.company.sneakerinvetory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SneakerInvetoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SneakerInvetoryApplication.class, args);
	}

}
