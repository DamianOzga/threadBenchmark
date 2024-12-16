package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class DataBaseWriteReadBenchmark extends AbstractThreadBenchmark {

    @Override
    public void codeToTest() {
        try {
            File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();
            UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);
            userService.save(userDTO);
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
        } catch (Exception exc) {
            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
        }
    }
}
