# threadBenchmark

Demo app for multi-threaded method benchmark.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Modules](#modules)
- [Profiles](#profiles)
- [Logging](#Logging)
- [Installation](#installation)
- [Extending Test Cases](#ExtendingTestCases)
- [Configuration](#configuration)
- [Benchmarking](#benchmarking)

## Introduction

`threadBenchmark` is a project designed to benchmark multi-threaded methods. It allows users to measure the performance of various methods under different threading conditions.

## Features

- Benchmark multi-threaded methods.
- Easy configuration and setup.
- Detailed performance reports.

## Modules

The project consists of the following modules:

1. **Benchmark**: This module contains the benchmark tests.
2. **ThreadBenchmarkApplication**: This is the main application module that is being tested.
3. **JmeterPerformanceApplication**: This module contains a very simple application that allows you to test it using jmeter.

## Profiles

The application supports profiles to configure the environment. The "dev" profile is available. Activating this profile switches the logging level to trace.
This is the basic functionality for demonstrating the capabilities of profiles in Spring Boot.

1. **Run profile "dev" add following parameter to VM:**
    ```bash
    -Dspring.profiles.active=dev
    ```

## Logging

- **logback-spring.xml**: This file is used to configure logging for different profiles.

## Installation

To install and run the `threadBenchmark` application, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/DamianOzga/threadBenchmark.git
    cd threadBenchmark
    ```

2. **Build the project:**
    ```bash
    ./mvn clean compile
    ./mvn package
    ```

3. **Run the Benchmark module:** 
     <BR><BR>Run main method from IDE level (MethodBenchmarkRunner.java).


4. **Run the ThreadBenchmarkApplication module:**
    ```bash
   # Run main method from IDE level
   Main class for running the Thread Benchmark Application. 
   
   ThreadBenchmarkApp.java
   
    # Build the Docker image
    cd ./ThreadBenchmarkApplication
    docker build -t thread-benchmark .

    # Run the application using Docker Compose
    cd ./ThreadBenchmarkApplication
    docker-compose up
    # Close Docker Compose
    docker-compose down
    ```
5. **Run the JmeterPerformanceApplication module:**
   ```bash
    ./mvn clean compile
    ./mvn package
   
   copy JmeterPerformanceApplication-**-dependencies.jar file
   to jmeter lib/ext folder and write your own Test Plans
   
    ```

## ExtendingTestCases

To use the `threadBenchmark` application, you need to define the methods you want to benchmark and configure the threading parameters. Here is a simple example:

1. **Define your benchmark class:**
    ```java
    package com.example.benchmark;

    import com.interview.damian_ozga.benchmark.AbstractThreadBenchmark;

    public class MyBenchmark extends AbstractThreadBenchmark {
        @Override
        public void setup() {
            // Setup code here
        }

        @Override
        public void codeToTest() {
            // Method to benchmark
        }
    }
    ```

2. **Include to runner configuration:**
    ```bash
          Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                ...
                .build();
    ```

## Configuration

You can configure various parameters for the benchmark, such as the number of threads, iterations, and more. Configuration is done through properties files or command-line arguments.

## Benchmarking

The application provides detailed performance reports after executing the benchmarks. These reports include metrics such as execution time, throughput, and resource utilization.
