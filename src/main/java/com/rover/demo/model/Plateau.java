package com.rover.demo.model;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Plateau {

    public int dimX;

    public int dimY;

    public Plateau(final int dimX, final int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
    }
}
