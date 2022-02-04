package com.learning.webfluxbasics.service;

import com.learning.webfluxbasics.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathsService {
    public Response findSquare(int input){
        return new Response(input*input);
    }
    public List<Response> multiplicationTable(int input){
        return IntStream.rangeClosed(1,10)
                .peek(i->SleepUtil.sleepSeconds(i))
                .peek(i-> System.out.println("math-service processing: "+i))
                .mapToObj(i->new Response(i*input))
                .collect(Collectors.toList());
    }
}
