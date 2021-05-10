package com.rover.demo.model;

import java.util.List;
import java.util.Random;

public enum Heading {

    NORTH,

    SOUTH,

    WEST,

    EAST;

    private static final List<Heading> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Heading getRandomHeading() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}

