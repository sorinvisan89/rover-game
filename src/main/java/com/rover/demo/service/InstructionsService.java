package com.rover.demo.service;

import com.rover.demo.exception.UnknownInstructionException;
import com.rover.demo.model.Instruction;

import java.util.ArrayList;
import java.util.List;

public class InstructionsService {

    final List<Instruction> instructions = new ArrayList<>();

    public InstructionsService(final String instructions) {

        for (char current : instructions.toCharArray()) {
            switch (current) {
                case 'L':
                    this.instructions.add(Instruction.LEFT);
                    break;
                case 'F':
                    this.instructions.add(Instruction.FORWARD);
                    break;
                case 'R':
                    this.instructions.add(Instruction.RIGHT);
                    break;
                case 'B':
                    this.instructions.add(Instruction.BACKWARDS);
                    break;
                default:
                    throw new UnknownInstructionException(current);
            }
        }
    }

    public List<Instruction> getInstructions() {
        return this.instructions;
    }
}
