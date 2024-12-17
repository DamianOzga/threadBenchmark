package com.interview.damian_ozga.test_base;

import com.interview.damian_ozga.ThreadBenchmarkApp;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base class for unit tests, setting up the Spring application context.
 * This class should be extended by all unit test classes to inherit the configuration.
 */
@SpringBootTest(classes = ThreadBenchmarkApp.class)
public abstract class AbstractUnitTest {
}