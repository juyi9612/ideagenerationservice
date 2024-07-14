package com.aifound.ideagenerationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@SpringBootApplication
@EnableCosmosRepositories(basePackages = "com.aifound.ideagenerationservice.repository")
@EnableConfigurationProperties
public class IdeaGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeaGenerationApplication.class, args);
	}

}
