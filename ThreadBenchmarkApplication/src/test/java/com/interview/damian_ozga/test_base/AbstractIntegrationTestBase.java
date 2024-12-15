package com.interview.damian_ozga.test_base;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import org.junit.jupiter.api.AfterAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = ThreadBenchmarkApp.class)
public class AbstractIntegrationTestBase {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0")
            .withExposedPorts(27017);

    @DynamicPropertySource
    static void containerProperties(DynamicPropertyRegistry registry){
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
    }

    @AfterAll
    void stop(){
        if(AbstractIntegrationTestBase.mongoDBContainer.isRunning()){
            AbstractIntegrationTestBase.mongoDBContainer.stop();
        }
    }
}
