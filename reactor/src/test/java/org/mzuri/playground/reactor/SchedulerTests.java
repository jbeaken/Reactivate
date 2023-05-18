package org.mzuri.playground.reactor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class SchedulerTests {

    Logger logger = LoggerFactory.getLogger(SchedulerTests.class);

    @Test
    void testScheduler() throws InterruptedException {

        logger.info("Starting scheduler tests");

        Flux.just("hello")
                .log()
                .doOnNext(v -> System.out.println("just " + Thread.currentThread().getName()))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(v -> System.out.println("publish " + Thread.currentThread().getName()))
                .delayElements(Duration.ofMillis(500))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> System.out.println(v + " delayed " + Thread.currentThread().getName()));

        Thread.sleep(1000);
    }
}
