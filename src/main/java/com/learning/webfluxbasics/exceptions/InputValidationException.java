package com.learning.webfluxbasics.exceptions;

public class InputValidationException extends RuntimeException{
    private static final String MSG="Allowed range is 10-20";
    private final int input;

    public InputValidationException( int input) {
        super(MSG);
        this.input = input;
    }

    public int getInput() {
        return input;
    }
}
