import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.time.Duration;

public class ReactorTests {

    @Test
    public void testBasicFlux() {

        Flux<String> stringFlux = Flux.just("hello", "world");
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        Flux<? extends Serializable> merge = Flux.merge(stringFlux, interval);

        stringFlux.subscribe(System.out::println);
        merge.subscribe(System.out::println);
    }

    @Test
    public void testFourOfTenFlux() {

        Flux<Integer> range = Flux.range(0, 10);

        range.subscribe(System.out::println, null, null, subscription -> subscription.request(7));
    }

    @Test
    public void testDoOnNext() {
        Flux<Integer> range = Flux.range(0, 10);
    }

    @Test
    public void testFlatMap() {
        Flux<String> flux = Flux.just("hello", "world", "here", "we", "go");

        Flux<User> map = flux.map(s -> getUser(s));

        Flux<Mono<User>> map1 = flux.map(s -> getMonoUser(s));

        Flux<User> userFlux = flux.flatMap(s -> getMonoUser(s));

        userFlux.log().checkpoint().subscribe(System.out::println);

        flux.checkpoint().log().map(String::toUpperCase);
    }

    User getUser(String name) {
        return new User("test");
    }

    Mono<User> getMonoUser(String name) {
        return Mono.just(getUser(name));
    }

    @Test
    public void testFluxWithSubscription() {

        Flux<String> stringFlux = Flux.just("hello", "world");
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        stringFlux.subscribe(System.out::println, t -> System.err.println(t), () -> {}, subscription -> {subscription.request(2);});
    }


    @AfterEach
    public void afterEach() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class User {
        private String name;
    }
}
