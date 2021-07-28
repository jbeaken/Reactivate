package org.mzuri.playground.concurrency;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {


    Logger logger = LoggerFactory.getLogger(ExecutorTest.class);

    @Test
    void testExecutor() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(() -> {
            logger.info("On thread {}", Thread.currentThread().getName());
        });

        executorService.submit(() -> {
            logger.info("On thread {}", Thread.currentThread().getName());
        });

    }
}
