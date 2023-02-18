package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    Workout workout;
    Exercise exercise1 = new Exercise("pull-up");
    Exercise exercise2 = new Exercise("sit-up");

    @BeforeEach
    public void setup() {
        workout = new Workout("chest");
    }

    @Test
    public void WorkoutConstructorTest() {
        assertTrue(workout.getExercises().isEmpty());
        assertEquals("chest", workout.getWorkoutGoal());
    }

    @Test
    public void changeGoalTest() {
        workout.changeGoal("abs");
        assertEquals("abs", workout.getWorkoutGoal());

        workout.changeGoal("legs");
        assertEquals("legs", workout.getWorkoutGoal());
    }

    @Test
    public void addTest(){

        workout.add(exercise1);
        assertTrue(workout.getExercises().contains(exercise1));

    }

    @Test
    public void addMultiTest(){
        workout.add(exercise1);
        assertTrue(workout.getExercises().contains(exercise1));
        workout.add(exercise2);
        assertTrue(workout.getExercises().contains(exercise2));

    }

    @Test
    public void removeTest(){
        workout.add(exercise1);
        assertTrue(workout.getExercises().contains(exercise1));
        workout.remove(exercise1);
        assertFalse(workout.getExercises().contains(exercise1));
        assertTrue(workout.getExercises().isEmpty());

    }

    @Test
    public void removeMultiTest(){
        workout.add(exercise1);
        workout.add(exercise2);
        assertTrue(workout.getExercises().contains(exercise1));
        assertTrue(workout.getExercises().contains(exercise2));
        workout.remove(exercise1);
        assertFalse(workout.getExercises().contains(exercise1));
        assertTrue(workout.getExercises().contains(exercise2));
        workout.remove(exercise2);
        assertFalse(workout.getExercises().contains(exercise2));
        assertTrue(workout.getExercises().isEmpty());

    }


    @Test
    public void getExercisesOneTest() {
    workout.add(exercise1);
    List<Exercise> results =  workout.getExercises();
    assertEquals(1, results.size());
    assertTrue(results.contains(exercise1));

    }

    @Test
    public void getExercisesTwoTest() {
        workout.add(exercise1);
        workout.add(exercise2);
        List<Exercise> results =  workout.getExercises();
        assertEquals(2, results.size());
        assertTrue(results.contains(exercise1));
        assertTrue(results.contains(exercise2));

    }


    @Test
    public void getWorkoutGoal() {
        assertEquals("chest", workout.getWorkoutGoal());
    }
}
