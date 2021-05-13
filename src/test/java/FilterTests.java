import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
class FilterTests {

    @Test
    void testFilter() {

        Flux<Integer> flux = Flux.just(10, 44, 45, 55, 33, 4, 55);

        flux.filter(x -> x > 30)
                .log()
                .subscribe();
    }

}
