package com.learn.model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.Chapter;

@Repository
public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String>{

	
}
