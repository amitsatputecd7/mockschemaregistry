package com.example.mockschema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import spark.Spark;

@SpringBootApplication
public class MockschemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockschemaApplication.class, args);

	
				// Test creating a client for a scope
				SchemaRegistryClient client = MockSchemaRegistry.getClientForScope("my-scope");
		
				// Test dropping a scope
				MockSchemaRegistry.dropScope("my-scope");
		
	}

}
