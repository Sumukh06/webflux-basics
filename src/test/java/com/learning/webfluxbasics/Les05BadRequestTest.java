package com.learning.webfluxbasics;

import com.learning.webfluxbasics.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Les05BadRequestTest extends BaseTest{
    @Autowired
    WebClient webClient;

    @Test
    void BadRequestTest() {
        Mono<Response> responseMono=webClient.get()
                .uri("/reactive-validation/square/{input}",1)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(throwable -> System.err.println(throwable));

        StepVerifier.create(responseMono)
                .verifyError(WebClientResponseException.BadRequest.class);
    }
}
