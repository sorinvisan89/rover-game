package com.rover.demo.exception;

public class InvalidPlateauDimensionsException extends RuntimeException {

    public InvalidPlateauDimensionsException(final int dimension) {
        super(String.format("Invalid dimension %d specified for Plateau !", dimension));
    }
}
