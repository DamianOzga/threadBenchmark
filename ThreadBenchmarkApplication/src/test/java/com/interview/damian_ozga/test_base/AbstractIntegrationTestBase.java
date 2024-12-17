package com.interview.damian_ozga.test_base;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import org.junit.jupiter.api.AfterAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Base class for integration tests, providing setup and teardown for MongoDB container.
 */
@Testcontainers
@SpringBootTest(classes = ThreadBenchmarkApp.class)
@TestPropertySource(locations = "classpath:application-IT.yaml")
public class AbstractIntegrationTestBase {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0")
            .withExposedPorts(27017);

    /**
     * Sets up dynamic properties for the MongoDB container.
     *
     * @param registry the property registry
     */
    @DynamicPropertySource
    static void containerProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getConnectionString);
    }

    /**
     * Stops the MongoDB container after all tests have run.
     */
    @AfterAll
    void stop() {
        if (AbstractIntegrationTestBase.mongoDBContainer.isRunning()) {
            AbstractIntegrationTestBase.mongoDBContainer.stop();
        }
    }
}