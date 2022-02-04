package com.learning.webfluxbasics.controller;

import com.learning.webfluxbasics.dto.Response;
import com.learning.webfluxbasics.exceptions.InputValidationException;
import com.learning.webfluxbasics.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-validation")
public class ReactiveMathValidationController {
    @Autowired
    ReactiveMathService mathService;

    @GetMapping("/square/{input}")
    public Mono<Response> sqrWithExc(@PathVariable int input){
        if(input<10||input>20)
            throw new InputValidationException(input);
        return mathService.findSquare(input);
    }
    @GetMapping("/square/{input}/mono")
    public Mono<Response> sqrWithExcReactive(@PathVariable int input){
       return Mono.just(input)
                .handle((integer, sink) -> {
                    if(integer>=10 && integer<=20)
                        sink.next(integer);
                    else
                        sink.error(new InputValidationException(integer));
                }).cast(Integer.class)
               .flatMap(integer -> mathService.findSquare(integer));
    }
    @GetMapping("/square/{input}/mono-assignment")
    public Mono <ResponseEntity<Response>> sqrWithExcAssignment(@PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(i -> mathService.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
