package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import com.interview.damian_ozga.service.ifc.UserService;
import com.interview.damian_ozga.utils.FileUtils;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;

public class DataBaseWriteReadRawDocumentBenchmark extends AbstractThreadBenchmark{

    private MongoTemplate mongoTemplate;
    private Document documentFromJSONFile;

    @Override
    public void setup() {
        context = SpringApplication.run(ThreadBenchmarkApp.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
    }

    @Override
    public void cleanup() {
        mongoTemplate.getCollection("benchmark").deleteOne(documentFromJSONFile);
    }

    @Override
    public void codeToTest() {
        try {
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();
        documentFromJSONFile = FileUtils.createDocumentFromJSONFile(benchmarkJSONFile);
        mongoTemplate.save(documentFromJSONFile, "benchmark");
        mongoTemplate.getCollection("benchmark").find().cursor().next();
        mongoTemplate.getCollection("benchmark").find().cursor().next();
        mongoTemplate.getCollection("benchmark").find().cursor().next();
        } catch (Exception exc) {
            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
        }
    }
}
