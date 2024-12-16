package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.utils.FileUtils;

import java.io.File;

public class DatabaseWriteReadWithReadyFileBenchmark extends AbstractThreadBenchmark{

    private UserDTO userDTO;

    @Override
    public void setup() {
        super.setup();
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();
        userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);
    }

    @Override
    public void codeToTest() {
        try {
            userService.save(userDTO);
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
            userService.getByKey(userDTO.getKey());
        } catch (Exception exc) {
            System.err.println("Exception caught in codeToTest: " + exc.getMessage());
        }
    }
}
