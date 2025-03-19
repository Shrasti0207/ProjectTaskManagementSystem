package com.example.ProjectTaskManagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// This class serves as the entry point to start the Spring Boot application.
@SpringBootApplication
@EnableCaching
public class ProjectTaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTaskManagementApplication.class, args);
	}

}
