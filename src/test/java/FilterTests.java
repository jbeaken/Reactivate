import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
class FilterTests {

    @Test
    void testFilter() {

        Flux<Integer> integerFlux = Flux.just(1, 4, 5, 7, 4, 8, 8, 8);

        integerFlux
                .filter(x -> x > 4)
                .distinct()
                .log()
                .subscribe();

    }

}
