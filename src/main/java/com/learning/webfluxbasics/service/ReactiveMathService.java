package com.learning.webfluxbasics.service;

import com.learning.webfluxbasics.dto.MultiplyRequest;
import com.learning.webfluxbasics.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input){
        return Mono.fromSupplier(()-> input*input )
                .map(Response::new);
    }

    public Flux<Response> findTable(int input){
        return Flux.range(1,10)
               // .doOnNext(i->SleepUtil.sleepSeconds(i))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.println("reactive math service processing: "+i))
                .map(i->new Response(i*input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequest> multiplyRequest){
        return multiplyRequest.map(v->new Response(v.getFirstNum()* v.getSecondNum()));

    }
}
