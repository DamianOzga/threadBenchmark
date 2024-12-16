package com.interview.damian_ozga.config.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    private final String connectionString;

    private final String databaseName;

    public MongoConfig(
            @Value("${spring.data.mongodb.uri}") String connectionString,
            @Value("${spring.data.mongodb.database}") String databaseName) {
        this.connectionString = connectionString;
        this.databaseName = databaseName;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(connectionString);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }
}
