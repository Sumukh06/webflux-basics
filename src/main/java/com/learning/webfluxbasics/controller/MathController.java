package com.learning.webfluxbasics.controller;

import com.learning.webfluxbasics.dto.Response;
import com.learning.webfluxbasics.service.MathsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {
    @Autowired
    MathsService mathsService;
    @GetMapping("square/{input}")
    public Response getSquare(@PathVariable int input){
        return mathsService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public List<Response> multiplicationTable(@PathVariable int input){
        return mathsService.multiplicationTable(input);
    }
}
