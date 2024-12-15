package com.interview.damian_ozga.benchmark.runner;

import com.interview.damian_ozga.benchmark.DataBaseWriteReadBenchmark;
import com.interview.damian_ozga.benchmark.DataBaseWriteReadRawDocumentBenchmark;
import com.interview.damian_ozga.benchmark.DataBaseWriteReadWithIndexBenchmark;
import com.interview.damian_ozga.benchmark.DatabaseCheckedWriteReadBenchmark;
import com.interview.damian_ozga.benchmark.DatabaseWriteReadWithReadyFileBenchmark;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;

public class MethodBenchmarkRunner {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(DataBaseWriteReadBenchmark.class.getSimpleName())
                .include(DatabaseCheckedWriteReadBenchmark.class.getSimpleName())
                .include(DataBaseWriteReadWithIndexBenchmark.class.getSimpleName())
                .include(DatabaseWriteReadWithReadyFileBenchmark.class.getSimpleName())
                .include(DataBaseWriteReadRawDocumentBenchmark.class.getSimpleName())
                .warmupIterations(1)
                .forks(1)
                .build();
        new Runner(opt).run();

       Collection<RunResult>  results = new Runner(opt).run();
    }
}
