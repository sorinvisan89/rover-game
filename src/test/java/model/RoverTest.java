package model;

import com.rover.demo.exception.InvalidPositionException;
import com.rover.demo.model.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverTest {

    private Plateau plateau;

    @Before
    public void setup() {
        plateau = new Plateau(20, 20);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void given_IllegalForwardMove_WhenRoverMoves_ShouldThrowException() {

        expectedEx.expect(InvalidPositionException.class);
        expectedEx.expectMessage("Position Position(x=3, y=20) is not on the plateau!");

        final Position initialPosition = new Position(3, 19);
        final Rover rover = new Rover(Heading.NORTH, plateau, initialPosition);
        rover.executeInstructions(Collections.singletonList(Instruction.FORWARD));
    }

    @Test
    public void given_IllegalBackwardsMove_WhenRoverMoves_ShouldThrowException() {

        expectedEx.expect(InvalidPositionException.class);
        expectedEx.expectMessage("Position Position(x=12, y=-1) is not on the plateau!");

        final Position initialPosition = new Position(12, 0);
        final Rover rover = new Rover(Heading.NORTH, plateau, initialPosition);
        rover.executeInstructions(Collections.singletonList(Instruction.BACKWARDS));
    }

    @Test
    public void given_ValidSetOfInstructions_WhenRoverReportPosition_ShouldReturnExpected() {

        final Position initialPosition = new Position(3, 3);
        final Rover rover = new Rover(Heading.NORTH, plateau, initialPosition);
        rover.executeInstructions(Arrays.asList(
                Instruction.FORWARD,
                Instruction.FORWARD, // 3 , 5
                Instruction.LEFT,
                Instruction.LEFT,
                Instruction.FORWARD, // 3, 4
                Instruction.RIGHT,
                Instruction.FORWARD) // 2, 4
        );

        final Position expected = new Position(2, 4);
        assertThat(rover.getPosition(), is(expected));
    }

}
