package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

    Exercise exercise1;
    Exercise exercise2;

    String e1Name = "pull-ups";

    @BeforeEach
    public void setup() {
        exercise1 = new Exercise(e1Name);
        exercise2 = new Exercise("plank");
    }

    // EFFECTS: makes exercise with name and pr set to 0
    @Test
    public void ExerciseConstructorTest() {
        assertEquals(e1Name, exercise1.getName());
        assertEquals(0, exercise1.getPr());
        assertEquals(0, exercise1.getReps());
        assertEquals(0, exercise1.getSets());
    }

    @Test
    public void newPrTest() {
        assertEquals(0,exercise1.getPr());
        exercise1.newPr(60);
        assertEquals(60, exercise1.getPr());
    }


    @Test
    public void changeNameTest() {

        assertEquals(e1Name,exercise1.getName());
        exercise1.setName("jump rope");
        assertEquals("jump rope", exercise1.getName());
    }

    @Test
    public void setRepsAndSetsTest() {

        exercise1.setReps(10);
        assertEquals(10, exercise1.getReps());
        exercise1.setReps(2);
        assertEquals(2, exercise1.getReps());
    }

    @Test
    public void setSetsTest() {

        exercise1.setSets(12);
        assertEquals(12, exercise1.getSets());
        exercise1.setSets(49);
        assertEquals(49, exercise1.getSets());
    }

}
