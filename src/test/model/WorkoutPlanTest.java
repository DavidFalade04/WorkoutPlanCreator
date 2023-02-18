package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class WorkoutPlanTest {

    WorkoutPlan myPlan;
    List<MuscleGroup> muscleGroups = new ArrayList<MuscleGroup>();
    Workout cardio = new Workout("cardio");
    Day monday = new Day("monday");
    Day tuesday = new Day("tuesday");
    Day wednesday = new Day("wednesday");
    List<Day> week = new ArrayList<>();


    @BeforeEach
    public void setup() {
        monday.setWorkout(cardio);
        wednesday.setWorkout(cardio);
        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        myPlan = new WorkoutPlan("my plan", week);
    }


    @Test
    public void WorkoutPlanConstructorTest(){
        myPlan = new WorkoutPlan("my plan", week);
        assertEquals("my plan", myPlan.getName());
        assertEquals(monday, myPlan.getDay("monday"));
        assertEquals(tuesday, myPlan.getDay("tuesday"));
        assertEquals(wednesday, myPlan.getDay("wednesday"));

    }

    @Test
    public void renameTest() {
        assertEquals("my plan", myPlan.getName());
        myPlan.rename("weight loss");
        assertEquals("weight loss", myPlan.getName());
    }


    @Test
    public void clearTest() {
        Day mday = myPlan.getDay("monday");
        assertNotEquals(null, mday.getWorkout());
        myPlan.clear();
        mday = myPlan.getDay("monday");
        assertEquals(null, mday.getWorkout());
    }


    @Test
    public void getDay() {
        assertEquals(monday, myPlan.getDay("monday"));
        assertEquals(tuesday, myPlan.getDay("tuesday"));

    }


}
