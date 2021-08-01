package org.mzuri.playground.concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {


    Logger logger = LoggerFactory.getLogger(ExecutorTest.class);

    String thread1Name;
    String thread2Name;

    @Test
    void testNewFixedThreadPoolRunsOnDifferentThreads() {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(() -> {
            thread1Name = getThreadName();
            logger.info("On thread {}", thread1Name);

        });

        executorService.submit(() -> {
            thread2Name = getThreadName();
            logger.info("On thread {}", thread2Name);
        });

        Assertions.assertNotEquals(thread1Name, thread2Name);
    }

    @Test
    void testNewSingleThreadExecutorRunsOnSameThread() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            thread1Name = getThreadName();
            logger.info("On thread {}", thread1Name);

        });

        executorService.submit(() -> {
            thread2Name = getThreadName();
            logger.info("On thread {}", thread2Name);
        });

        Assertions.assertEquals(thread1Name, thread2Name);
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }
}
