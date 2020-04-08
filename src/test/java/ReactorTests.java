import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;

@Slf4j
public class ReactorTests {

    @Test
    public void testZip() {
        Flux<String> flux1 = Flux.just("A", "B", "C", "D", "E");
        Flux<String> flux2 = Flux.just("1", "2", "3", "4", "5");

        flux1.delayElements(Duration.ofSeconds(2));

        flux2.delayElements(Duration.ofSeconds(4));

        //merge
        Flux merge = Flux.zip(flux1, flux2).log();

        StepVerifier.create(merge).expectNext(List.of("A", "1")).verifyComplete();
    }

    @Test
    public void testMerge() {
        Flux<String> flux1 = Flux.just("A", "B", "C", "D", "E").delayElements(Duration.ofSeconds(1));;
        Flux<String> flux2 = Flux.just("1", "2", "3", "4", "5").delayElements(Duration.ofSeconds(2));;

        Flux merge = Flux.merge(flux1, flux2).log();

        StepVerifier.create(merge).expectNext("A", "B", "C", "D", "E", "1", "2", "3", "4", "5").verifyComplete();
    }

    @Test
    public void testOperators() {
        Flux<String> flux = Flux.just("hello", "world", "here", "we", "go");

        Flux<User> map = flux.map(s -> getUser(s));

        Flux<Mono<User>> map1 = flux.map(s -> getMonoUser(s));

        Flux<User> userFlux = flux.flatMap(s -> getMonoUser(s));

        flux.checkpoint()
                .log()
                .map(String::toUpperCase)
                .publishOn(Schedulers.elastic())
                .flatMap(s -> getMonoUser(s))
                .subscribe(s -> log.info(s.toString()));
    }

    User getUser(String name) {
        return new User(name);
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
