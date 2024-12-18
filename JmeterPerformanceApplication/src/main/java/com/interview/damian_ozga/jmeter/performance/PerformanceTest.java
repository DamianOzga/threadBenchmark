package com.interview.damian_ozga.jmeter.performance;

import com.interview.damian_ozga.jmeter.config.MongoDbConfig;
import com.interview.damian_ozga.jmeter.service.MongoDocumentService;
import com.interview.damian_ozga.jmeter.utils.FileUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.bson.Document;

import java.io.File;

/**
 * Performance test class for MongoDB operations using JMeter.
 * It implements JavaSamplerClient to integrate with JMeter for performance testing.
 */
public class PerformanceTest implements JavaSamplerClient {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;
    private MongoDocumentService mongoDocumentService;
    private Document documentFromJSONFile;

    /**
     * Default constructor.
     */
    public PerformanceTest() {
    }

    /**
     * Setup method to initialize MongoDB configuration and services before each test.
     *
     * @param javaSamplerContext the context providing parameters for the test
     */
    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        MongoDbConfig config = new MongoDbConfig();
        mongoClient = config.getMongoClient();
        mongoDatabase = config.getDatabase(mongoClient);
        mongoCollection = config.getCollection(mongoDatabase);
        mongoDocumentService = new MongoDocumentService(mongoCollection);
    }

    /**
     * Runs the performance test by inserting a document into MongoDB and performing multiple read operations.
     *
     * @param javaSamplerContext the context providing parameters for the test
     * @return the result of the test
     */
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();

        // Retrieve the JSON file to be benchmarked
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();

        // Convert the JSON file to a MongoDB Document
        documentFromJSONFile = FileUtils.createDocumentFromJSONFile(benchmarkJSONFile);

        // Insert the document into the MongoDB collection
        mongoDocumentService.insertDoc(documentFromJSONFile);

        // Retrieve the key value for the inserted document
        String keyValue = (String) documentFromJSONFile.get("key");

        // Perform multiple read operations using the key
        mongoDocumentService.findByKey("key", keyValue);
        mongoDocumentService.findByKey("key", keyValue);
        mongoDocumentService.findByKey("key", keyValue);

        // End the sample and mark it as successful
        sampleResult.sampleEnd();
        sampleResult.setSuccessful(true);

        return sampleResult;
    }

    /**
     * Teardown method to clean up MongoDB and close the client connection after each test.
     *
     * @param javaSamplerContext the context providing parameters for the test
     */
    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        // Delete the inserted documents from MongoDB
        mongoCollection.deleteMany(documentFromJSONFile);

        // Close the MongoDB client connection
        mongoClient.close();
    }

    /**
     * Provides default parameters for the JMeter test.
     *
     * @return the default arguments for the test
     */
    @Override
    public Arguments getDefaultParameters() {
        return null;
    }
}