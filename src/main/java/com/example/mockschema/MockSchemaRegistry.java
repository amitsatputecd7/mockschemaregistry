package com.example.mockschema;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import org.apache.kafka.common.config.ConfigException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class MockSchemaRegistry {
    private static final String MOCK_URL_PREFIX = "mock://";
    private static final Map<String, SchemaRegistryClient> SCOPED_CLIENTS = new HashMap<>();

    public static SchemaRegistryClient getClientForScope(final String scope) {
        synchronized (SCOPED_CLIENTS) {
            if (!SCOPED_CLIENTS.containsKey(scope)) {
                SCOPED_CLIENTS.put(scope, new MockSchemaRegistryClient());
            }
        }
        return SCOPED_CLIENTS.get(scope);
    }

    public static void dropScope(final String scope) {
        synchronized (SCOPED_CLIENTS) {
            SCOPED_CLIENTS.remove(scope);
        }
    }

    public static String validateAndMaybeGetMockScope(final List<String> urls) {
        final List<String> mockScopes = new LinkedList<>();
        for (final String url : urls) {
            if (url.startsWith(MOCK_URL_PREFIX)) {
                mockScopes.add(url.substring(MOCK_URL_PREFIX.length()));
            }
        }
        if (mockScopes.isEmpty()) {
            return null;
        } else if (mockScopes.size() > 1) {
            throw new ConfigException(
                    "Only one mock scope is permitted for 'schema.registry.url'. Got: " + urls
            );
        } else if (urls.size() > mockScopes.size()) {
            throw new ConfigException(
                    "Cannot mix mock and real urls for 'schema.registry.url'. Got: " + urls
            );
        } else {
            return mockScopes.get(0);
        }
    }
}
