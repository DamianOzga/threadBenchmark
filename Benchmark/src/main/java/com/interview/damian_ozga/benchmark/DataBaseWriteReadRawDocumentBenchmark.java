package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import com.interview.damian_ozga.utils.FileUtils;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;

/**
 * It performs write and read operations on a MongoDB collection using raw BSON documents.
 */
public class DataBaseWriteReadRawDocumentBenchmark extends AbstractThreadBenchmark {

    private MongoTemplate mongoTemplate;
    private Document documentFromJSONFile;

    /**
     * Sets up the Spring application context and initializes the MongoTemplate bean before each iteration.
     */
    @Override
    public void setup() {
        context = SpringApplication.run(ThreadBenchmarkApp.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
    }

    /**
     * Cleans up the test data from the MongoDB collection after each iteration.
     */
    @Override
    public void cleanUp() {
        mongoTemplate.getCollection("benchmark").deleteOne(documentFromJSONFile);
    }

    /**
     * Executes the code to be tested in the benchmark.
     * It reads a BSON document from a JSON file, saves the document to the MongoDB collection,
     * and performs multiple read operations.
     */
    @Override
    public void codeToTest() {
        try {
            // Retrieve the JSON file containing the benchmark data
            File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();

            // Create a BSON Document from the JSON file
            documentFromJSONFile = FileUtils.createDocumentFromJSONFile(benchmarkJSONFile);

            // Save the document to the MongoDB collection
            mongoTemplate.save(documentFromJSONFile, "benchmark");

            // Perform multiple read operations to get the document from the collection
            mongoTemplate.getCollection("benchmark").find().cursor().next();
            mongoTemplate.getCollection("benchmark").find().cursor().next();
            mongoTemplate.getCollection("benchmark").find().cursor().next();
        } catch (Exception exc) {
            // Handle any exceptions that occur during the benchmark
            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
        }
    }
}