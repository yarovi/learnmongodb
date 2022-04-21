package com.learn;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

//import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractReactiveMongoConfiguration{

	//@Value("${mongo.uri}")
	@Value("${spring.data.mongodb.uri}")
	String mongo;

	@Value("${spring.data.mongodb.database}")
	private String db;
	
	@Override
	public MongoClient reactiveMongoClient() {
		// TODO Auto-generated method stub
		//return super.reactiveMongoClient();
		return MongoClients.create(mongo);
	}


	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return db;
	}

	
	
	
}
