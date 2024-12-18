package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.service.ifc.UserService;
import com.interview.damian_ozga.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class DBWriteReadParamNoOfThreads implements Benchmarkable{

    @Param({"1", "2", "4", "6", "10", "16"})
    public int numberOfThreads;
    protected ExecutorService executorService;
    protected ConfigurableApplicationContext context;
    protected UserService userService;

    /**
     * Sets up the Spring application context and initializes the UserService bean before each iteration.
     */
    @Setup(Level.Iteration)
    public void setup(){
        context = getApplicationContext();
        userService = getUserService(context);
    }

    @Override
    public void cleanUp() {
        userService.deleteAll();
        FileUtils.cleanupTestJSONFile();
        context.close();
    }

    @Setup(Level.Invocation)
    public void innerSetup(){
        executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    @TearDown(Level.Invocation)
    public void innerCleanup() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.err.println("Executor service interrupted.");
        }
    }


    public void codeToTest() {
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        File benchmarkJSONFile = FileUtils.getBenchmarkJSONFile();

                        // Map the JSON file to a UserDTO object
                        UserDTO userDTO = FileUtils.mapJsonFileToClass(benchmarkJSONFile, UserDTO.class);

                        // Save the user to the database
                        userService.save(userDTO);

                        // Perform multiple read operations to get the user by the key
                        userService.getByKey(userDTO.getKey());
                        userService.getByKey(userDTO.getKey());
                        userService.getByKey(userDTO.getKey());
                    } catch (Exception exc) {
                        // Handle any exceptions that occur during the benchmark
                        System.err.println("ERROR! " + exc.getMessage());
                    }
                }
            });
        }
    }

    @Benchmark
    @Measurement(iterations = 1, time = 60, timeUnit = TimeUnit.SECONDS)
    public void concurrencyThreads() {
        codeToTest();
    }
}
