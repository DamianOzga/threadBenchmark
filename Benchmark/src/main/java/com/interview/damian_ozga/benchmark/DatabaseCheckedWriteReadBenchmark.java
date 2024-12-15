package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.utils.FileUtils;

import java.io.File;

public class DatabaseCheckedWriteReadBenchmark extends AbstractThreadBenchmark{

    @Override
    public void codeToTest() {
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();
        UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);
        Boolean exists = userService.existByKey(userDTO.getKey());
        if(!exists){
            userService.save(userDTO);
        }
        userService.getByKey(userDTO.getKey());
        userService.getByKey(userDTO.getKey());
        userService.getByKey(userDTO.getKey());
    }
}
