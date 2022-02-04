package com.learning.webfluxbasics.controller;

import com.learning.webfluxbasics.dto.MultiplyRequest;
import com.learning.webfluxbasics.dto.Response;
import com.learning.webfluxbasics.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
    @Autowired
    private ReactiveMathService mathService;
    @GetMapping("square/{input}")
    public Mono<Response> getSquare(@PathVariable int input){
        return mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return mathService.findTable(input);
    }
    @GetMapping(value = "table/{input}/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input){
        return mathService.findTable(input);
    }
    @PostMapping("/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequest> mulObj,@RequestHeader Map<String,String> header){
        System.out.println(header.toString());
        return mathService.multiply(mulObj);
    }
}
