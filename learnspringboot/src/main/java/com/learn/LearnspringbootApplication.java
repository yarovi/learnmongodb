package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class LearnspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnspringbootApplication.class, args);
	}

}
