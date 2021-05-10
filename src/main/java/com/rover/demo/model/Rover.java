package com.rover.demo.model;

import com.rover.demo.exception.InvalidPositionException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Rover {

    private Heading heading;

    private Plateau plateau;
    private Position position;

    public String reportPosition() {
        final StringBuilder sb = new StringBuilder();
        sb.append(position.getX());
        sb.append(" ");
        sb.append(position.getY());
        sb.append(" ");
        sb.append(heading);
        return sb.toString();
    }

    public void executeInstructions(final List<Instruction> instructionList) {
        instructionList.forEach(this::processInstruction);
    }

    private void processInstruction(final Instruction instruction) {

        switch (instruction) {
            case LEFT:
                turnLeft();
                break;
            case BACKWARDS:
                moveBackwards();
                break;
            case RIGHT:
                turnRight();
                break;
            case FORWARD:
                moveForward();
                break;
        }
    }

    private void turnRight() {
        switch (heading) {
            case EAST:
                heading = Heading.SOUTH;
                break;
            case NORTH:
                heading = Heading.EAST;
                break;
            case SOUTH:
                heading = Heading.WEST;
                break;
            case WEST:
                heading = Heading.NORTH;
                break;
        }
    }

    private void turnLeft() {
        switch (heading) {
            case EAST:
                heading = Heading.NORTH;
                break;
            case NORTH:
                heading = Heading.WEST;
                break;
            case SOUTH:
                heading = Heading.EAST;
                break;
            case WEST:
                heading = Heading.SOUTH;
                break;
        }
    }

    private void moveForward() {
        final Position newPosition = position.moveForward(heading);
        if (!newPosition.isValidMove(plateau)) {
            throw new InvalidPositionException(plateau, newPosition);
        }
        position = newPosition;
    }

    private void moveBackwards() {
        final Position newPosition = position.moveBackwards(heading);
        if (!newPosition.isValidMove(plateau)) {
            throw new InvalidPositionException(plateau, newPosition);
        }
        position = newPosition;
    }
}
