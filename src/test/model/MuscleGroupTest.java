package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MuscleGroupTest {

    MuscleGroup chest;
    MuscleGroup biceps;
    Exercise curls = new Exercise("curls");
    Exercise chinUp = new Exercise( "chinUp");
    List<Exercise> bicepExercises = new ArrayList<Exercise>();
    Exercise pushUp = new Exercise("pushUp");
    Exercise BenchPress = new Exercise("Bench Press");
    List<Exercise> chestExercises = new ArrayList<Exercise>();

    @BeforeEach
    public void setup() {
        chest = new MuscleGroup("chest");
        biceps = new MuscleGroup("biceps");
        bicepExercises.add(curls);
        bicepExercises.add(chinUp);
        chestExercises.add(pushUp);
        chestExercises.add(BenchPress);
    }

    @Test
    public void MuscleGroupConstructorTest() {
        biceps = new MuscleGroup("bicep");
        assertEquals("bicep", biceps.getName());
    }

    @Test
    public void populateTest() {
        assertTrue(biceps.getExercises().isEmpty());
        biceps.populate(bicepExercises);
        assertTrue(biceps.getExercises().containsAll(bicepExercises));

    }

    @Test
    public void populateTwiceTest() {
        assertTrue(biceps.getExercises().isEmpty());
        biceps.populate(bicepExercises);
        biceps.populate(chestExercises);
        assertTrue(biceps.getExercises().containsAll(bicepExercises));
        assertTrue(biceps.getExercises().containsAll(chestExercises));

    }


}
