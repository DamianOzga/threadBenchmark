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

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public abstract class AbstractThreadBenchmark {

    protected ConfigurableApplicationContext context;
    protected UserService userService;

    @Setup(Level.Iteration)
    public void setup(){
        context = SpringApplication.run(ThreadBenchmarkApp.class);
        userService = context.getBean(UserService.class);
    };

    @TearDown(Level.Iteration)
    public void cleanup(){
        userService.deleteAll();
        FileUtils.cleanupTestJSONFile();
    }

    @Benchmark
    @Measurement(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS) // 60 seconds
    @Threads(1)
    public void concurrencyThreads_1(){
        codeToTest();
    }

//    @Benchmark
//    @Measurement(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Threads(2)
//    public void concurrencyThreads_2(){
//        codeToTest();
//    };

    @Benchmark
    @Measurement(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS) // 60 seconds
    @Threads(4)
    public void concurrencyThreads_4(){
        codeToTest();
    };

//    @Benchmark
//    @Measurement(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Threads(6)
//    public void concurrencyThreads_6(){
//        codeToTest();
//    };

    @Benchmark
    @Measurement(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS) // 60 seconds
    @Threads(10)
    public void concurrencyThreads_10(){
        codeToTest();
    };

//    @Benchmark
//    @Measurement(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Threads(16)
//    public void concurrencyThreads_16(){
//        codeToTest();
//    };


    public abstract void codeToTest();
}
