package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import com.interview.damian_ozga.service.ifc.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public interface Benchmarkable {

    void setup();

    void cleanUp();

    default ConfigurableApplicationContext getApplicationContext(){
        return SpringApplication.run(ThreadBenchmarkApp.class);
    }

    default UserService getUserService(ConfigurableApplicationContext context){
        return context.getBean(UserService.class);
    }
}
