package com.ubs.accumulator.exceptions;


public class AccumulatorException extends RuntimeException{

    private String message;

    public AccumulatorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
