package com.learn.model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.Image;

import reactor.core.publisher.Mono;

@Repository
public interface ImageRepository extends ReactiveCrudRepository<Image, String>{

	Mono<Image> findByName(String name);
}
