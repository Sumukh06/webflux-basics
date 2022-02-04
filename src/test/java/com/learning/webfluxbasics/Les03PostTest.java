package com.learning.webfluxbasics;

import com.learning.webfluxbasics.dto.MultiplyRequest;
import com.learning.webfluxbasics.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Les03PostTest extends BaseTest{
    @Autowired
    WebClient webClient;

    @Test
    void PostTest() {
        Mono<Response> responseMono=webClient.post()
                .uri("reactive-math/multiply")
                .bodyValue(buildObj(5,10))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getOutput()==50)
                .verifyComplete();

    }
    public MultiplyRequest buildObj(int a,int b){
        MultiplyRequest obj=new MultiplyRequest();
        obj.setFirstNum(a);
        obj.setSecondNum(b);
        return obj;
    }
}
