package com.interview.damian_ozga.jmeter.performance;

import com.interview.damian_ozga.jmeter.config.MongoDbConfig;
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

import static com.interview.damian_ozga.jmeter.utils.FileUtils.getBenchmarkJSONFile;

public class PerformanceTest implements JavaSamplerClient {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;

    public PerformanceTest() {
    }

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        MongoDbConfig config = new MongoDbConfig();
        mongoClient = config.getMongoClient();
        mongoDatabase = config.getDatabase(mongoClient);
        mongoCollection = config.getCollection(mongoDatabase);
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        File benchmarkJSONFile = getBenchmarkJSONFile();
        // Create a document to insert
        Document doc = new Document("name", "John Doe")
                .append("email", "john.doe@example.com")
                .append("age", 29);

        // Insert the document into the collection
        InsertOneResult insertOneResult = mongoCollection.insertOne(doc);
        System.out.println("Document inserted successfully");

        // Retrieve the document from the collection
        FindIterable<Document> iterable = mongoCollection.find(new Document("name", "John Doe"));
        for (Document document : iterable) {
            System.out.println("Retrieved document: " + document.toJson());
        }
        sampleResult.sampleEnd();
        sampleResult.setSuccessful(true);
        return sampleResult;
    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }

    @Override
    public Arguments getDefaultParameters() {
        return null;
    }
}
