package com.rover.demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Position {

    public int x;
    public int y;

    public boolean isValidMove(final Plateau plateau) {
        if (this.x < 0 || x > plateau.getDimX() -1) {
            return false;
        }

        return this.y >= 0 && y <= plateau.getDimY() - 1;
    }

    public Position moveForward(final Heading heading) {
        switch (heading) {
            case EAST:
                return new Position(x + 1, y);
            case NORTH:
                return new Position(x, y + 1);
            case SOUTH:
                return new Position(x, y - 1);
            case WEST:
                return new Position(x - 1, y);
            default:
                throw new UnsupportedOperationException(
                        String.format("Heading %s not implemented for moving forward!", heading)
                );
        }
    }

    public Position moveBackwards(final Heading heading) {
        switch (heading) {
            case EAST:
                return new Position(x - 1, y);
            case NORTH:
                return new Position(x, y - 1);
            case SOUTH:
                return new Position(x, y + 1);
            case WEST:
                return new Position(x + 1, y);
            default:
                throw new UnsupportedOperationException(
                        String.format("Heading %s not implemented for moving backwards!", heading)
                );
        }
    }
}
