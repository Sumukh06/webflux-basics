package com.learning.webfluxbasics;

import com.learning.webfluxbasics.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01GetSingleResponseTest extends BaseTest{

    @Autowired
    WebClient webClient;

    @Test
    void BlockTest() {
        Response response=webClient.get()
                .uri("reactive-math/square/{input}",7)
                .retrieve()
                .bodyToMono(Response.class)
                .block();
        System.out.println(response);

    }

    @Test
    void StepTest() {
        Mono<Response> responseMono=webClient.get()
                .uri("reactive-math/square/{input}",9)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getOutput()==81)
                .verifyComplete();
    }
}
