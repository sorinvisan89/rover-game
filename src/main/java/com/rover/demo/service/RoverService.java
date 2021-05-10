package com.rover.demo.service;

import com.rover.demo.model.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RoverService {

    private final InstructionsService instructionsService;
    private final Rover rover;
    private final PositionWithHeading initial;

    private static final Random RANDOM = new Random();

    public RoverService(
            final String commandInput,
            final int dimX,
            final int dimY,
            final PositionWithHeading initialPosition) {

        this.instructionsService = new InstructionsService(commandInput);
        final Plateau plateau = new Plateau(dimX, dimY);

        initial = Optional.ofNullable(initialPosition)
                .orElse(randomInitialPosition(plateau));

        this.rover = new Rover(initial.getHeading(), plateau, initial.getPosition());
    }

    public void executeCommands() {
        final List<Instruction> instructionList = this.instructionsService.getInstructions();
        rover.executeInstructions(instructionList);
    }

    public String reportPosition() {
        return this.rover.reportPosition();
    }

    public PositionWithHeading getPosition() {
        return this.initial;
    }

    private PositionWithHeading randomInitialPosition(final Plateau plateau) {
        final int XPosition = RANDOM.nextInt(plateau.getDimX());
        final int YPosition = RANDOM.nextInt(plateau.getDimY());
        final Heading heading = Heading.getRandomHeading();

        return PositionWithHeading.builder()
                .position(new Position(XPosition, YPosition))
                .heading(heading)
                .build();
    }
}
