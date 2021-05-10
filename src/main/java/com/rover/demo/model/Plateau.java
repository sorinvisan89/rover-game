package com.rover.demo.model;

import com.rover.demo.exception.InvalidPlateauDimensionsException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Plateau {

    public int dimX;

    public int dimY;

    public Plateau(final int dimX, final int dimY) {
        if (dimX < 1) {
            throw new InvalidPlateauDimensionsException(dimX);
        }

        if (dimY < 1) {
            throw new InvalidPlateauDimensionsException(dimY);
        }

        this.dimX = dimX;
        this.dimY = dimY;
    }

    @Override
    public String toString() {
        return String.format("Plateau (dimX=%d , dimY=%d)", dimX - 1, dimY - 1);
    }
}
