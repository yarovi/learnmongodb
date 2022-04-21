package com.learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.learn.data.Chapter;
import com.learn.model.ChapterRepository;

import reactor.core.publisher.Flux;

@Configuration

public class LoadDatabase {

	/*
	@Bean
	CommandLineRunner init(ChapterRepository repo) {
		
		return args -> {
			Flux.just(
					new Chapter("Quick Start with java"),
					new Chapter("Reactive Web with Spring Boot"),
					new Chapter("...and more!"))
					.flatMap(repo::save)
					.subscribe(System.out::println);
		};
	}
	*/
}
