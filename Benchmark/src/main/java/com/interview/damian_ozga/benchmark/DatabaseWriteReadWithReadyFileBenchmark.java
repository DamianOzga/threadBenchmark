package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.utils.FileUtils;

import java.io.File;

/**
 * It performs write and read operations on a database using a UserDTO object loaded from a JSON file.
 */
public class DatabaseWriteReadWithReadyFileBenchmark extends AbstractThreadBenchmark {

    private UserDTO userDTO;

    /**
     * Sets up the benchmark environment by initializing the UserDTO object
     * from a JSON file before each iteration.
     */
    @Override
    public void setup() {
        super.setup();
        // Retrieve the JSON file containing the benchmark data
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();

        // Map the JSON file to a UserDTO object
        userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);
    }

    /**
     * Executes the code to be tested in the benchmark.
     * It saves the UserDTO object to the database and performs multiple read operations using the key.
     */
    @Override
    public void codeToTest() {
        try {
            // Save the user to the database
            userService.save(userDTO);

            // Perform multiple read operations to get the user by key
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
        } catch (Exception exc) {
            // Handle any exceptions that occur during the benchmark
            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
        }
    }
}