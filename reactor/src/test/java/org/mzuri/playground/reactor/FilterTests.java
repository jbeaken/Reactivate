package org.mzuri.playground.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
class FilterTests {

    @Test
    void test() {

        Integer[] intArray = {1, 2, 3, 4, 4, 5, 6, 7, 7, 7};

        Flux<Integer> flux = Flux.just(intArray);

        flux
                .filter( x -> x > 4)
                .distinct()
                .log().subscribe();

    }

}
