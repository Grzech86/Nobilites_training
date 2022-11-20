package Nobilites.ex48;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

//# Reactive Test
//        Implement test for reactive stream producing elements 1, 2, 3, 4, 5,
//        filtering odd number, incrementing by 1
public class TestEx48 {

    @Test
    public void testFlux() {
        Flux<Integer> flux = Flux
                .just(1, 2, 3, 4, 5)
                .filter(v -> (v % 2 == 0))
                .map(v -> v + 1);

        StepVerifier
                .create(flux)
                .expectNext(3, 5)
                .verifyComplete();
    }

}