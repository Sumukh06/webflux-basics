package com.learning.webfluxbasics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;

public class Lec06QueryParamTest extends BaseTest{
    @Autowired
    WebClient webClient;
    URI uri= UriComponentsBuilder.fromUriString
            ("http://localhost:8080/jobs/search?count={count}&page={page}").build(5,1);
    @Test
    void TestQueryParam() {
        Flux<Integer> integerFlux=webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextMatches(integer -> integer.equals(5))
                .expectNextCount(1)
                .verifyComplete();
    }
}
