package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import com.interview.damian_ozga.service.ifc.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Interface representing a benchmarkable component.
 * Provides methods for setup and cleanup, as well as default methods
 * to obtain application context and user service.
 */
public interface Benchmarkable {

    /**
     * Sets up the necessary components or environment for the benchmark.
     */
    void setup();

    /**
     * Cleans up the components or environment after the benchmark.
     */
    void cleanUp();

    /**
     * Obtains the application context by running the ThreadBenchmarkApp.
     *
     * @return the application context
     */
    default ConfigurableApplicationContext getApplicationContext(){
        return SpringApplication.run(ThreadBenchmarkApp.class);
    }

    /**
     * Retrieves the UserService bean from the given application context.
     *
     * @param context the application context
     * @return the UserService bean
     */
    default UserService getUserService(ConfigurableApplicationContext context){
        return context.getBean(UserService.class);
    }
}