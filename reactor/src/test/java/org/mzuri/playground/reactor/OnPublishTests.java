package org.mzuri.playground.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


class OnPublishTests {


    @Test
    void testPublishOn() {

        Flux.just(1, 2, 3, 4)
                .doOnNext(s -> System.out.println(s + " before publishOn using thread: " + Thread.currentThread().getName()))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(s -> System.out.println(s + " after publishOn using thread: " + Thread.currentThread().getName()))
                .subscribe();

    }

}
