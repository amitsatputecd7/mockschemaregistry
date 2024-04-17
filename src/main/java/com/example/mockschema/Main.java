package com.example.mockschema;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
public class Main {
    public static void main(String[] args) {
        // Test creating a client for a scope
        SchemaRegistryClient client = MockSchemaRegistry.getClientForScope("my-scope");

        // Simulate continuous operation
        while (true) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
