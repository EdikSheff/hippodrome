import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


public class HorseTest {

    @Test
    public void null_Name_Exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void null_Name_Message() {
        String expected = "Name cannot be null.";
        try {
            new Horse (null, 1, 1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void blank_Name_Exception_With_Message (String name) {
        String expected = "Name cannot be blank.";
        try {
            new Horse (name, 1, 1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -2.5, -50.68, -100.111})
    public void negative_Speed_Exception_With_Message (double speed) {
        String expected = "Speed cannot be negative.";
        try {
            new Horse ("Name", speed, 1);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(expected, e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -2.5, -50.68, -100.111})
    public void negative_Distance_Exception_With_Message (double distance) {
        String expected = "Distance cannot be negative.";
        try {
            new Horse ("Name", 1, distance);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void getName() {
        String expected = "Name";
        Horse horse = new Horse("Name", 1, 1);
        Assertions.assertEquals(expected, horse.getName());
    }

    @Test
    public void getSpeed() {
        double expected = 15.6;
        Horse horse = new Horse("Name", 15.6, 1);
        Assertions.assertEquals(expected, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        double expected = 538.6;
        Horse horse = new Horse("Name", 15.6, 538.6);
        Assertions.assertEquals(expected, horse.getDistance());
    }

    @Test
    public void getDistance_Zero_Constructor_With_Two_Parameters() {
        double expected = 0;
        Horse horse = new Horse("Name", 15.6);
        Assertions.assertEquals(expected, horse.getDistance());
    }

    @Test
    void move_With_getRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("Name", 15.6, 538.6).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
