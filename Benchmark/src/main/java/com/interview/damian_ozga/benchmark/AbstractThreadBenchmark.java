package com.interview.damian_ozga.benchmark;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import com.interview.damian_ozga.service.ifc.UserService;
import com.interview.damian_ozga.utils.FileUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * AbstractThreadBenchmark is an abstract class designed to benchmark different concurrency levels
 * using the JMH (Java Microbenchmark Harness) framework. It sets up the Spring application context
 * and performs benchmarks with varying numbers of threads.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public abstract class AbstractThreadBenchmark {

    protected ConfigurableApplicationContext context;
    protected UserService userService;

    /**
     * Sets up the Spring application context and initializes the UserService bean before each iteration.
     */
    @Setup(Level.Iteration)
    public void setup(){
        context = SpringApplication.run(ThreadBenchmarkApp.class);
        userService = context.getBean(UserService.class);
    }

    /**
     * Cleans up the test data and context after each iteration.
     */
    @TearDown(Level.Iteration)
    public void cleanup(){
        userService.deleteAll();
        FileUtils.cleanupTestJSONFile();
    }

    /**
     * Benchmark method to test the code with 1 thread.
     */
    @Benchmark
    @Measurement(iterations = 1, time = 3, timeUnit = TimeUnit.MINUTES)
    @Threads(1)
    public void concurrencyThreads_1(){
        codeToTest();
    }

    /**
     * Benchmark method to test the code with 2 threads.
     */
    @Benchmark
    @Measurement(iterations = 1, time = 3, timeUnit = TimeUnit.MINUTES)
    @Threads(2)
    public void concurrencyThreads_2(){
        codeToTest();
    }

    /**
     * Benchmark method to test the code with 4 threads.
     */
    @Benchmark
    @Measurement(iterations = 1, time = 3, timeUnit = TimeUnit.MINUTES)
    @Threads(4)
    public void concurrencyThreads_4(){
        codeToTest();
    }

    /**
     * Benchmark method to test the code with 6 threads.
     */
    @Benchmark
    @Measurement(iterations = 1, time = 3, timeUnit = TimeUnit.MINUTES)
    @Threads(6)
    public void concurrencyThreads_6(){
        codeToTest();
    }

    /**
     * Benchmark method to test the code with 10 threads.
     */
    @Benchmark
    @Measurement(iterations = 1, time = 3, timeUnit = TimeUnit.MINUTES)
    @Threads(10)
    public void concurrencyThreads_10(){
        codeToTest();
    }

    /**
     * Benchmark method to test the code with 16 threads.
     */
    @Benchmark
    @Measurement(iterations = 1, time = 3, timeUnit = TimeUnit.MINUTES)
    @Threads(16)
    public void concurrencyThreads_16(){
        codeToTest();
    }

    /**
     * Abstract method to be implemented by subclasses, containing the code to be tested in the benchmarks.
     */
    public abstract void codeToTest();
}