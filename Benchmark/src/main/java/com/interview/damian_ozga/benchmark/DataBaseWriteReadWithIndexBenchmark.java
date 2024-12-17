package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.model.User;
import com.interview.damian_ozga.utils.FileUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import java.io.File;

/**
 * It performs write and read operations on a MongoDB collection with an index on the email field.
 */
public class DataBaseWriteReadWithIndexBenchmark extends AbstractThreadBenchmark {

    private MongoTemplate mongoTemplate;

    /**
     * Sets up the Spring application context and initializes the MongoTemplate bean before each iteration.
     * It also ensures that an index on the email field is created for the User collection.
     */
    @Override
    public void setup() {
        super.setup();
        // Initialize the MongoTemplate bean from the Spring application context
        MongoTemplate bean = context.getBean(MongoTemplate.class);
        // Ensure an index on the email field in the User collection
        bean.indexOps(User.class).ensureIndex(new Index().on("email", Sort.Direction.ASC));
    }

    /**
     * Executes the code to be tested in the benchmark.
     * It reads a UserDTO from a JSON file, saves the user to the database,
     * and performs multiple read operations using the email field.
     */
    @Override
    public void codeToTest() {
        try {
            // Retrieve the JSON file containing the benchmark data
            File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();

            // Map the JSON file to a UserDTO object
            UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);

            // Save the user to the database
            userService.save(userDTO);

            // Perform multiple read operations to get the user by email
            userService.findByEmail(userDTO.getEmail());
            userService.findByEmail(userDTO.getEmail());
            userService.findByEmail(userDTO.getEmail());
        } catch (Exception exc) {
            // Handle any exceptions that occur during the benchmark
            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
        }
    }
}