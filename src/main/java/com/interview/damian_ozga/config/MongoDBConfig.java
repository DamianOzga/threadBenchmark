//package com.interview.damian_ozga.config;
//
//import com.mongodb.client.MongoClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MongoDBConfig {
//
//    private final String connectionString;
//
//    public MongoDBConfig(String connectionString) {
//        this.connectionString = connectionString;
//    }
//
//    @Bean
//    public MongoClient mongoClient(){
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//    }
//}
