package com.mlatta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.mlatta.brewclient.model.properties")
public class SfgBreweryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfgBreweryClientApplication.class, args);
	}

}
