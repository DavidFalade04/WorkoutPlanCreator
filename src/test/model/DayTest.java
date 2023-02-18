package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    Day monday;
    List<MuscleGroup> muscleGroups = new ArrayList<MuscleGroup>();
    Workout cardio = new Workout("cardio" );
    Workout legs = new Workout("legs");

    @BeforeEach
    public void setUp() {
        monday = new Day("monday");
    }

    @Test
    public void DayConstructorTest() {
        monday = new Day("monday");
        assertEquals("monday", monday.getName());
        assertEquals("rest", monday.getStatus());
    }

    @Test
    public void setWorkoutTest() {
       assertEquals(null, monday.getWorkout());
       monday.setWorkout(cardio);
       assertEquals(cardio, monday.getWorkout());
    }

    @Test
    public void setWorkoutTwiceTest() {
        monday.setWorkout(cardio);
        assertEquals(cardio, monday.getWorkout());
        monday.setWorkout(legs);
        assertEquals(legs, monday.getWorkout());
    }


    @Test
    public void removeWorkoutTest(){
        monday.setWorkout(cardio);
        monday.removeWorkout();
        assertEquals(null, monday.getWorkout());

    }


    @Test
    public void setStatus() {
    monday.setStatus("legs");
    assertEquals("legs", monday.getStatus());

    }

}
