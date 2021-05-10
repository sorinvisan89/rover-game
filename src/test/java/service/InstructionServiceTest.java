package service;


import com.rover.demo.exception.UnknownInstructionException;
import com.rover.demo.model.Instruction;
import com.rover.demo.service.InstructionsService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class InstructionServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void given_validInput_WhenExecuting_ShouldReturnExpected() {
        final String input = "FFRFBLLB";
        final InstructionsService instructionsService = new InstructionsService(input);

        final List<Instruction> actual = instructionsService.getInstructions();
        final List<Instruction> expected = Arrays.asList(
                Instruction.FORWARD,
                Instruction.FORWARD,
                Instruction.RIGHT,
                Instruction.FORWARD,
                Instruction.BACKWARDS,
                Instruction.LEFT,
                Instruction.LEFT,
                Instruction.BACKWARDS
        );

        assertThat(expected, is(actual));
    }

    @Test
    public void given_InvalidInput_WhenExecuting_ShouldThrowException() {

        expectedEx.expect(UnknownInstructionException.class);
        expectedEx.expectMessage("Unknown instruction for: 'X'");

        final String input = "FLXB";
        new InstructionsService(input);
        fail("Exception should have been thrown at this point");
    }

}
