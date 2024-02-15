package org.example.server;

import org.example.server.Properties.StorageProperties;
import org.example.server.service.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Main entry point for the server application.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ServerApplication {

    /**
     * Main method to run the Spring Boot application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    /**
     * Bean to perform initialization tasks when the application starts. <br/>
     * For Production use this would not be here.
     * This serves as database and every time new instance of converter application starts
     * storage gets initialized
     *
     * @param fileService The FileService to perform file-related operations.
     * @return A CommandLineRunner instance to execute the initialization tasks.
     */
    @Bean
    CommandLineRunner init(FileService fileService) {
        return (args) -> fileService.deleteAll();
    }
}
