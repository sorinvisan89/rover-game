package com.rover.demo.exception;

import com.rover.demo.model.Plateau;
import com.rover.demo.model.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidPositionException extends RuntimeException {

    private final Plateau plateau;
    private final Position position;

    public InvalidPositionException(final Plateau plateau, final Position position) {
        super(String.format("Position %s is not on the plateau!", position));

        this.plateau = plateau;
        this.position = position;
    }
}
