import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {
    @Test
    public void null_Horses_Exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    public void null_Horses_Message() {
        String expected = "Horses cannot be null.";
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void Empty_Horses_Exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }
    @Test
    public void Empty_Horses_Message() {
        String expected = "Horses cannot be empty.";
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void getHorses() {
        List<Horse> horses  = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + (i+1), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }
    @Test
    public void getWinner() {
        Horse horse1 = new Horse("Horse 1", 15, 500);
        Horse horse2 = new Horse("Horse 2", 15, 100);
        Horse horse3 = new Horse("Horse 3", 15, 50);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        Assertions.assertEquals(horse1, hippodrome.getWinner());
    }
}
