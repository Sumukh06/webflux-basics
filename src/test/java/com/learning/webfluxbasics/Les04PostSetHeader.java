package com.learning.webfluxbasics;


import com.learning.webfluxbasics.dto.MultiplyRequest;
import com.learning.webfluxbasics.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class Les04PostSetHeader extends BaseTest{
    @Autowired
    WebClient webClient;

    @Test
    void PostWithHeader() {
        Mono<Response> responseMono = webClient.post()
                .uri("reactive-math/multiply")
                .bodyValue(buildObj(5,5))
               // .header("Auth","Admin")
                .headers(httpHeaders -> httpHeaders.set("key-String","value-String"))
                .headers(httpHeaders -> httpHeaders.setBearerAuth("Complex jwt token"))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getOutput()==25)
                .verifyComplete();
    }
    public MultiplyRequest buildObj(int a, int b){
        MultiplyRequest obj=new MultiplyRequest();
        obj.setFirstNum(a);
        obj.setSecondNum(b);
        return obj;
    }
}
