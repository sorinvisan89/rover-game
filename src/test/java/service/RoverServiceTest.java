package service;

import com.rover.demo.exception.InvalidPositionException;
import com.rover.demo.model.Heading;
import com.rover.demo.model.Position;
import com.rover.demo.model.PositionWithHeading;
import com.rover.demo.service.RoverService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverServiceTest {

    private static final int PLATEAU_X_DIMENSION = 20;
    private static final int PLATEAU_Y_DIMENSION = 20;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void given_validMoveCommandsAndStartPosition_WhenReportingStatus_ShouldReturnExpected() {
        final String commands = "FFFBBLFFRRFFBBL";
        final PositionWithHeading initialPosition = PositionWithHeading.builder()
                .heading(Heading.EAST)
                .position(new Position(12, 15))
                .build();

        final RoverService roverService = new RoverService(commands, PLATEAU_X_DIMENSION, PLATEAU_Y_DIMENSION, initialPosition);
        roverService.executeCommands();
        final String actual = roverService.reportPosition();
        assertThat(actual, is("13 17 EAST"));
    }

    @Test
    public void given_valid360CommandsAndStartPosition_WhenReportingStatus_ShouldReturnExpected() {
        final String commands = "FLFLFLFL";
        final PositionWithHeading initialPosition = PositionWithHeading.builder()
                .heading(Heading.NORTH)
                .position(new Position(10, 11))
                .build();

        final RoverService roverService = new RoverService(commands, PLATEAU_X_DIMENSION, PLATEAU_Y_DIMENSION, initialPosition);
        roverService.executeCommands();
        final String actual = roverService.reportPosition();
        assertThat(actual, is("10 11 NORTH"));
    }

    @Test
    public void given_InvalidMoveCommandsAndStartPosition_WhenExecutingCommands_ShouldThrowException() {

        expectedEx.expect(InvalidPositionException.class);
        expectedEx.expectMessage("Position Position(x=-1, y=13) is not on the plateau!");

        final String commands = "FFLFFFFFFFFFFF";
        final PositionWithHeading initialPosition = PositionWithHeading.builder()
                .heading(Heading.NORTH)
                .position(new Position(10, 11))
                .build();

        final RoverService roverService = new RoverService(commands, PLATEAU_X_DIMENSION, PLATEAU_Y_DIMENSION, initialPosition);
        roverService.executeCommands();
    }

    @Test
    public void given_RandomStartPositionAndMultipleIterations_WhenExecutingCommands_ShouldReturnExpected() {
        final String commands = "FLF";

        for (int iteration = 0; iteration < 1000; iteration++) {

            final RoverService roverService = new RoverService(commands, PLATEAU_X_DIMENSION, PLATEAU_Y_DIMENSION, null);
            final PositionWithHeading initialPosition = roverService.getPosition();

            // Since we are moving a predifined FLF, we need to check we have a valid random position
            if (!checkRandomInitial(initialPosition)) {
                System.out.println(String.format("Skipping current iteration %d due to possible incompatible random position %s",iteration, initialPosition));
                continue;
            }

            roverService.executeCommands();

            final String movedRoverPosition = roverService.reportPosition();

            // Dynamically adjust the expected rover position based on the FLF movements and input
            final String expected = new StringBuilder()
                    .append(switchXBasedOnHeading(initialPosition.getPosition().getX(), initialPosition.getHeading())) // LF // F
                    .append(" ")
                    .append(switchYBasedOnHeading(initialPosition.getPosition().getY(), initialPosition.getHeading())) // LF
                    .append(" ")
                    .append(switchHeading(initialPosition.getHeading())) // the new heading is different from the original position
                    .toString();

            assertThat(movedRoverPosition, is(expected));
        }
    }

    private boolean checkRandomInitial(final PositionWithHeading position) {
        if (position.getHeading().equals(Heading.NORTH) && position.getPosition().getY() == 19) {
            return false;
        }
        if (position.getHeading().equals(Heading.NORTH) && position.getPosition().getX() == 0) {
            return false;
        }
        if (position.getHeading().equals(Heading.EAST) && position.getPosition().getX() == 19) {
            return false;
        }
        if (position.getHeading().equals(Heading.EAST) && position.getPosition().getY() == 19) {
            return false;
        }
        if (position.getHeading().equals(Heading.WEST) && position.getPosition().getX() == 0) {
            return false;
        }
        if (position.getHeading().equals(Heading.WEST) && position.getPosition().getY() == 0) {
            return false;
        }
        if (position.getHeading().equals(Heading.SOUTH) && position.getPosition().getY() == 0) {
            return false;
        }
        if (position.getHeading().equals(Heading.SOUTH) && position.getPosition().getX() == 19) {
            return false;
        }
        return true;
    }

    private String switchHeading(final Heading intialHeading) {
        switch (intialHeading) {
            case NORTH:
                return Heading.WEST.name();
            case SOUTH:
                return Heading.EAST.name();
            case EAST:
                return Heading.NORTH.name();
            case WEST:
                return Heading.SOUTH.name();
            default:
                throw new IllegalArgumentException(String.format("No supported heading %s", intialHeading));
        }
    }

    private int switchXBasedOnHeading(final int dimX, final Heading initialHeading) {
        switch (initialHeading) {
            case EAST:
            case SOUTH:
                return dimX + 1;
            case NORTH:
            case WEST:
                return dimX - 1;
            default:
                throw new IllegalArgumentException(String.format("No supported heading %s", initialHeading));
        }
    }

    private int switchYBasedOnHeading(final int dimY, final Heading initialHeading) {
        switch (initialHeading) {
            case NORTH:
            case EAST:
                return dimY + 1;
            case SOUTH:
            case WEST:
                return dimY - 1;
            default:
                throw new IllegalArgumentException(String.format("No supported heading %s", initialHeading));
        }
    }


}
