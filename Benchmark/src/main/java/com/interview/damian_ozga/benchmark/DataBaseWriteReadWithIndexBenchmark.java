package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.model.User;
import com.interview.damian_ozga.utils.FileUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import java.io.File;

public class DataBaseWriteReadWithIndexBenchmark extends AbstractThreadBenchmark {

    private MongoTemplate mongoTemplate;

    @Override
    public void setup() {
        super.setup();
        MongoTemplate bean = context.getBean(MongoTemplate.class);
        bean.indexOps(User.class).ensureIndex(new Index().on("email", Sort.Direction.ASC));
    }

    @Override
    public void codeToTest() {
        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();
        UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);
        userService.save(userDTO);
        userService.findByEmail(userDTO.getEmail());
        userService.findByEmail(userDTO.getEmail());
        userService.findByEmail(userDTO.getEmail());
    }
}
