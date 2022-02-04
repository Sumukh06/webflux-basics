package com.learning.webfluxbasics.dto;

import lombok.Data;

@Data
public class InputFailedValidation {
    private int input;
    private String message;
}
