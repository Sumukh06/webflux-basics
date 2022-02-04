package com.learning.webfluxbasics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiplyRequest {
    private int firstNum;
    private int secondNum;
}
