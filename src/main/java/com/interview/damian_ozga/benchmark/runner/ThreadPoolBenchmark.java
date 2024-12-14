package com.interview.damian_ozga.benchmark.runner;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.service.ifc.IUserService;
import org.openjdk.jmh.annotations.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.interview.damian_ozga.utils.FileUtils.*;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ThreadPoolBenchmark {

    private ConfigurableApplicationContext context;
    private IUserService userService;

    public static void main(String... args) {
        File testJSONFile = createTestJSONFile();
        UserDTO userDTO = mapJsonFileToClass(testJSONFile, UserDTO.class);
        cleanupTestJSONFile();
    }

    @Setup(Level.Trial)
    public void setup() {
        context = SpringApplication.run(ThreadBenchmarkApp.class);
        userService = context.getBean(IUserService.class);
    }

    @Benchmark
    @Warmup(iterations = 1)
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS) // 60 seconds
    @Fork(1)
    @Threads(1)
    public void Thread1() {
        UserDTO.builder().name("Tny").build();
        userService.save(UserDTO.builder().name("Tny").build());
    }

//    //    @Benchmark
////    @Warmup(iterations = 1)
////    @Threads(2)
////    @Measurement(time = 18000)
////    @Fork(1)
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(2)
//    public void Thread2() {
////        long endTime = System.currentTimeMillis() + 60000;
////        while (System.currentTimeMillis() < endTime) {
//        Document document = new Document("key=2", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
////        }
//    }
//
//    //    @Benchmark
////    @Warmup(iterations = 1)
////    @Threads(3)
////    @Measurement(iterations = 1, time = 180)
////    @Fork(1)
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(3)
//    public void Thread3() {
//        Document document = new Document("key=3", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }
//
//    //    @Benchmark
////    @Warmup(iterations = 1)
////    @Threads(4)
////    @Measurement(iterations = 1, time = 180)
////    @Fork(1)
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(4)
//    public void Thread4() {
//        Document document = new Document("key=4", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }
//
//    //    @Benchmark
////    @Warmup(iterations = 1)
////    @Threads(5)
////    @Measurement(iterations = 1, time = 180)
////    @Fork(1)
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(5)
//    public void Thread5() {
//        Document document = new Document("key=5", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }
//
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(6)
//    public void Thread6() {
//        Document document = new Document("key=6", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }
//
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(7)
//    public void Thread7() {
//        Document document = new Document("key=7", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }
//
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(8)
//    public void Thread8() {
//        Document document = new Document("key=8", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }
//
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(9)
//    public void Thread9() {
//        Document document = new Document("key=9", "value");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }
//    //    @Benchmark
////    @Warmup(iterations = 1)
////    @Threads(10)
////    @Measurement(iterations = 1, time = 180)
////    @Fork(1)
//    @Benchmark
//    @Warmup(iterations = 1)
//    @Measurement(iterations = 1, time = 180, timeUnit = TimeUnit.SECONDS) // 60 seconds
//    @Fork(1)
//    @Threads(10)
//    public void Thread10() {
//        Document document = new Document("key=10", "value1");
//        benchamark.insertOne(document);
//        benchamark.find(document);
//        benchamark.find(document);
//        benchamark.find(document);
//    }

    @TearDown
    public void closeConnection() {
//        mongoClient.close();
    }
}
