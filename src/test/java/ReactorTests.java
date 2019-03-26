import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.time.Duration;

public class ReactorTests {

    @Test
    public void testFlux() {

        Flux<String> stringFlux = Flux.just("hello", "world");
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        Flux<? extends Serializable> merge = Flux.merge(stringFlux, interval);

        stringFlux.subscribe(System.out::println);
        merge.subscribe(System.out::println);
    }

    @AfterEach
    public void afterEach() throws InterruptedException {
        Thread.sleep(20000);
    }
}
