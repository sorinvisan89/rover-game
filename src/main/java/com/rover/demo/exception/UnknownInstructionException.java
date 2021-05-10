package com.rover.demo.exception;

public class UnknownInstructionException extends RuntimeException {

    public UnknownInstructionException(final char instruction) {
        super("Unknown instruction for: '" + instruction + "'!");
    }
}