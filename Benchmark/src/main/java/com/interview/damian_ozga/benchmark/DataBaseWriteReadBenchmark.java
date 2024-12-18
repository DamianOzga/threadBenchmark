package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * It performs write and read operations on a database using data from a JSON file.
 */
@Slf4j
public class DataBaseWriteReadBenchmark extends AbstractThreadBenchmark {

    /**
     * Executes the code to be tested in the benchmark.
     * It reads a UserDTO from a JSON file, saves the user to the database,
     * and performs multiple read operations.
     */
    @Override
    public void codeToTest() {
//        try {
            // Retrieve the JSON file containing the benchmark data
            File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();

            // Map the JSON file to a UserDTO object
            UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);

            // Save the user to the database
            userService.save(userDTO);

            // Perform multiple read operations to get the user by the key
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
//        } catch (Exception exc) {
//            // Handle any exceptions that occur during the benchmark
//            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
//        }
    }
}