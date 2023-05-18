package org.mzuri.playground.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class MonoVsFlatMap {

    @Test
    void testMap() {
        Function<String, String> mapper = String::toUpperCase;
        Flux<String> inFlux = Flux.just("baeldung", ".", "com");
        Flux<String> outFlux = inFlux.map(mapper);

        outFlux.as(StepVerifier::create)
                .expectNext("BAELDUNG", ".", "COM")
                .expectComplete()
                .verify();

    }

    @Test
    void testFlatMap() {
        Function<String, Publisher<String>> mapper = s -> Flux.just(s.toUpperCase().split(""));

        Flux<String> inFlux = Flux.just("baeldung", ".", "com");
        Flux<String> outFlux = inFlux.flatMap(mapper);

        List<String> output = new ArrayList<>();
        outFlux.subscribe(output::add);
        assertThat(output).containsExactlyInAnyOrder("B", "A", "E", "L", "D", "U", "N", "G", ".", "C", "O", "M");

    }
}
