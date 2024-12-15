package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.utils.FileUtils;

import java.io.File;

public class DataBaseWriteReadBenchmark extends AbstractThreadBenchmark {

    @Override
    public void codeToTest() {
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();
        UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);
        userService.save(userDTO);
        userService.getByKey(userDTO.getKey());
        userService.getByKey(userDTO.getKey());
        userService.getByKey(userDTO.getKey());
    }
}
