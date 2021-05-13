import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


class OnPublishTests {


    @Test
    void testPublishOn() {

        Flux.just(1, 2, 3, 4)
                // this is influenced by subscribeOn
                .doOnNext(s -> System.out.println(s + " before publishOn using thread: " + Thread.currentThread().getName()))
                .publishOn(Schedulers.boundedElastic())
                // the rest is influenced by publishOn
                .doOnNext(s -> System.out.println(s + " after publishOn using thread: " + Thread.currentThread().getName()))
                .subscribe();

    }

}
