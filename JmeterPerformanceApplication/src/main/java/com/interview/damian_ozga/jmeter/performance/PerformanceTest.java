package com.interview.damian_ozga.jmeter.performance;

import com.interview.damian_ozga.jmeter.config.MongoDbConfig;
import com.interview.damian_ozga.jmeter.service.MongoDocumentService;
import com.interview.damian_ozga.jmeter.utils.FileUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.bson.Document;

import java.io.File;

public class PerformanceTest implements JavaSamplerClient {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;
    private MongoDocumentService mongoDocumentService;
    private Document documentFromJSONFile;

    public PerformanceTest() {
    }

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        MongoDbConfig config = new MongoDbConfig();
        mongoClient = config.getMongoClient();
        mongoDatabase = config.getDatabase(mongoClient);
        mongoCollection = config.getCollection(mongoDatabase);
        mongoDocumentService = new MongoDocumentService(mongoCollection);

    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();
        documentFromJSONFile = FileUtils.createDocumentFromJSONFile(benchmarkJSONFile);
        // Create a document to insert

        // Insert the document into the collection
        mongoDocumentService.insertDoc(documentFromJSONFile);

//        InsertOneResult insertOneResult = mongoCollection.insertOne(documentFromJSONFile);
        System.out.println("Document inserted successfully");

        String keyValue = (String) documentFromJSONFile.get("key");
        mongoDocumentService.findByKey("key", keyValue);
        mongoDocumentService.findByKey("key", keyValue);
        mongoDocumentService.findByKey("key", keyValue);

        sampleResult.sampleEnd();
        sampleResult.setSuccessful(true);
        return sampleResult;
    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        mongoCollection.deleteMany(documentFromJSONFile);
        mongoClient.close();
    }

    @Override
    public Arguments getDefaultParameters() {
        return null;
    }
}
