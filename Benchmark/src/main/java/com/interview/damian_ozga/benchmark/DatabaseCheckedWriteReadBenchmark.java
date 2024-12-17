package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.utils.FileUtils;

import java.io.File;

/**
 * It performs read and write operations on a database, ensuring that data is checked before being written.
 */
public class DatabaseCheckedWriteReadBenchmark extends AbstractThreadBenchmark {

    /**
     * Executes the code to be tested in the benchmark.
     * It reads a UserDTO from a JSON file, checks if the user exists in the database,
     * saves the user if it does not exist, and performs multiple read operations.
     */
    @Override
    public void codeToTest() {
        try {
            // Retrieve the JSON file containing the benchmark data
            File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();

            // Map the JSON file to a UserDTO object
            UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);

            // Check if the user exists in the database by the user's key
            Boolean exists = userService.existByKey(userDTO.getKey());

            // If the user does not exist, save the user to the database
            if (!exists) {
                userService.save(userDTO);
            }

            // Perform multiple read operations to get the user by the key
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
        } catch (Exception exc) {
            // Handle any exceptions that occur during the benchmark
            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
        }
    }
}