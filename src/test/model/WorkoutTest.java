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
    MuscleGroup biceps = new MuscleGroup("biceps");
    MuscleGroup chest = new MuscleGroup("chest");
    List<MuscleGroup> muscleGroups = new ArrayList<MuscleGroup>();


    @BeforeEach
    public void setup() {
        muscleGroups.add(biceps);
        muscleGroups.add(chest);
        workout = new Workout("chest", muscleGroups);
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
    public void getMuscleGroupsTest() {
        List<MuscleGroup> result = workout.getMuscleGroups();
        assertTrue(result.contains(biceps));
        assertTrue(result.contains(chest));
        assertEquals(2, result.size());
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
