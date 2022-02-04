package com.learning.webfluxbasics;

import com.learning.webfluxbasics.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec02GetFluxResponseTest extends BaseTest{
    
    @Autowired
    WebClient webClient;

    @Test
    void FluxTest() {
        Flux<Response> responseFlux=webClient.get()
                .uri("reactive-math/table/{input}",99)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseFlux)
                .expectNextMatches(response -> response.getOutput()==99)
                .expectNextMatches(response -> response.getOutput()==198)
                .expectNextCount(8)
                .verifyComplete();
    }

    @Test
    void FluxStreamTest() {
        Flux<Response> responseFlux=webClient.get()
                .uri("reactive-math/table/{input}/stream",7)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseFlux)
                .expectNextMatches(response -> response.getOutput()==7)
                .expectNextMatches(r->r.getOutput()==14)
                .expectNextCount(8)
                .verifyComplete();
    }
}
