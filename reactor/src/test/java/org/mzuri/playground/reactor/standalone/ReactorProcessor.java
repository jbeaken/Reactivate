package org.mzuri.playground.reactor.standalone;

import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.*;
import reactor.core.scheduler.Schedulers;

public class ReactorProcessor {

    /**
     * Attempt 1 - use a DirectProcessor and send items to it.
     * Doesn't work though - seems to always run on the main thread.
     */
    @Test
    public void testReact1() throws InterruptedException {
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    while (true) {
                        fluxSink.next(System.currentTimeMillis());
                    }
                })
                .publish();

        publish.subscribe(System.out::println);

        Disposable disposable = publish.connect();

        // It's multi-thread so wait a sec to receive.
        Thread.sleep(5000);

        disposable.dispose();
    }


}
