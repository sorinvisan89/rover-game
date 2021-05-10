package model;

import com.rover.demo.exception.InvalidPlateauDimensionsException;
import com.rover.demo.model.Plateau;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlateauTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void given_InvalidXDimension_whenCreatingPlateau_ShouldThrowException() {

        expectedEx.expect(InvalidPlateauDimensionsException.class);
        expectedEx.expectMessage("Invalid dimension -222 specified for Plateau !");


        new Plateau(-222, 20);

    }

    @Test
    public void given_InvalidYDimension_whenCreatingPlateau_ShouldThrowException() {

        expectedEx.expect(InvalidPlateauDimensionsException.class);
        expectedEx.expectMessage("Invalid dimension 0 specified for Plateau !");

        new Plateau(3, 0);
    }

    @Test
    public void given_ValidYDimension_whenCreatingPlateau_ShouldExpected() {
        final Plateau plateau = new Plateau(1, 45);

        assertThat(plateau.getDimX(), is(1));
        assertThat(plateau.getDimY(), is(45));
    }

}
