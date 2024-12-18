package com.interview.damian_ozga.benchmark.runner;

import com.interview.damian_ozga.benchmark.DBWriteReadParamNoOfThreads;
import com.interview.damian_ozga.benchmark.DataBaseWriteReadBenchmark;
import com.interview.damian_ozga.benchmark.DataBaseWriteReadRawDocumentBenchmark;
import com.interview.damian_ozga.benchmark.DataBaseWriteReadWithIndexBenchmark;
import com.interview.damian_ozga.benchmark.DatabaseCheckedWriteReadBenchmark;
import com.interview.damian_ozga.benchmark.DatabaseWriteReadWithReadyFileBenchmark;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;

/**
 * The MethodBenchmarkRunner class is responsible for setting up and running
 * various database benchmark tests using the JMH (Java Microbenchmark Harness) framework.
 */
public class MethodBenchmarkRunner {

    /**
     * The main method sets up the JMH runner with the specified benchmark classes
     * and runs the benchmarks, outputting the results to a CSV file.
     *
     * @param args Command line arguments (not used).
     * @throws Exception If an error occurs during the benchmark execution.
     */
    public static void main(String[] args) throws Exception {
        // Configure the JMH options for the benchmarks
        Options opt = new OptionsBuilder()
                .include(DataBaseWriteReadBenchmark.class.getSimpleName())
                .include(DBWriteReadParamNoOfThreads.class.getSimpleName())
                .include(DatabaseCheckedWriteReadBenchmark.class.getSimpleName())
                .include(DataBaseWriteReadWithIndexBenchmark.class.getSimpleName())
                .include(DatabaseWriteReadWithReadyFileBenchmark.class.getSimpleName())
                .include(DataBaseWriteReadRawDocumentBenchmark.class.getSimpleName())
                .warmupIterations(0) // Set the number of warmup iterations to 0
                .result("results.csv") // Set the result file name
                .resultFormat(ResultFormatType.CSV) // Set the result format to CSV
                .forks(1) // Set the number of forks to 1
                .shouldFailOnError(false)
                .build();

        // Run the benchmarks with the specified options
        new Runner(opt).run();

        // Collect the benchmark results
        Collection<RunResult> results = new Runner(opt).run();
    }
}