package model;

import com.rover.demo.model.Heading;
import com.rover.demo.model.Position;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PositionTest {

    @Test
    public void given_ValidMoveForward_WhenHeadingNorth_ShouldReturnExpected() {
        Position position = new Position(5, 6);
        final Position newPosition = position.moveForward(Heading.NORTH);
        assertThat(newPosition.getX(), is(5));
        assertThat(newPosition.getY(), is(7));
    }

    @Test
    public void given_ValidMoveBackwards_WhenHeadingNorth_ShouldReturnExpected() {
        final Position position = new Position(5, 6);
        final Position newPosition = position.moveBackwards(Heading.NORTH);
        assertThat(newPosition.getX(), is(5));
        assertThat(newPosition.getY(), is(5));
    }

    @Test
    public void given_ValidMoveForward_WhenHeadingSouth_ShouldReturnExpected() {
        final Position position = new Position(5, 6);
        final Position newPosition = position.moveForward(Heading.SOUTH);
        assertThat(newPosition.getX(), is(5));
        assertThat(newPosition.getY(), is(5));
    }

    @Test
    public void given_ValidMoveBackwards_WhenHeadingSouth_ShouldReturnExpected() {
        final Position position = new Position(5, 6);
        final Position newPosition = position.moveBackwards(Heading.SOUTH);
        assertThat(newPosition.getX(), is(5));
        assertThat(newPosition.getY(), is(7));
    }

    @Test
    public void given_ValidMoveForward_WhenHeadingWest_ShouldReturnExpected() {
        final Position position = new Position(5, 6);
        final Position newPosition = position.moveForward(Heading.WEST);
        assertThat(newPosition.getX(), is(4));
        assertThat(newPosition.getY(), is(6));
    }

    @Test
    public void given_ValidMoveBackwards_WhenHeadingWest_ShouldReturnExpected() {
        final Position position = new Position(5, 6);
        final Position newPosition = position.moveBackwards(Heading.WEST);
        assertThat(newPosition.getX(), is(6));
        assertThat(newPosition.getY(), is(6));
    }

    @Test
    public void given_ValidMoveForward_WhenHeadingEast_ShouldReturnExpected() {
        final Position position = new Position(5, 6);
        final Position newPosition = position.moveForward(Heading.EAST);
        assertThat(newPosition.getX(), is(6));
        assertThat(newPosition.getY(), is(6));
    }

    @Test
    public void given_ValidMoveBackwards_WhenHeadingEast_ShouldReturnExpected() {
        final Position position = new Position(5, 6);
        final Position newPosition = position.moveBackwards(Heading.EAST);
        assertThat(newPosition.getX(), is(4));
        assertThat(newPosition.getY(), is(6));
    }


}
