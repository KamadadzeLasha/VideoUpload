package org.example.server;

import org.example.server.Properties.StorageProperties;
import org.example.server.service.FileConversionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(FileConversionService conversionService) {
		return (args) -> {
			conversionService.deleteAll();
			conversionService.initialize();
		};
	}
}
