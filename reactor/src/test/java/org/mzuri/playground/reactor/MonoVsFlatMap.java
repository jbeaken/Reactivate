package org.mzuri.playground.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

public class MonoVsFlatMap {

    @Test
    void testFlux.() {
        Function<String, String> mapper = String::toUpperCase;
        Flux<String> inFlux = Flux.just("baeldung", ".", "com");
        Flux<String> outFlux = inFlux.map(mapper);

        outFlux.as(StepVerifier::create)
                .expectNext("BAELDUNG", ".", "COM")
                .expectComplete()
                .verify();

    }
}
