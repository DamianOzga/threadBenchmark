package com.interview.damian_ozga.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MethodBenchmarkRunner {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(ThreadPoolBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
