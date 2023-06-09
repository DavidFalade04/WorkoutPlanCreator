package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

import static org.junit.jupiter.api.Assertions.*;


public class WorkoutPlanTest {

    WorkoutPlan myPlan;
    List<MuscleGroup> muscleGroups = new ArrayList<MuscleGroup>();
    Workout cardio = new Workout("cardio");
    Day monday = new Day("monday");
    Day tuesday = new Day("tuesday");
    Day wednesday = new Day("wednesday");
    Day thursday = new Day("thursday");
    Day friday = new Day("friday");
    Day saturday = new Day("saturday");
    Day sunday = new Day("sunday");
    List<Day> week = new ArrayList<>();


    @BeforeEach
    public void setup() {
        monday.setWorkout(cardio);
        wednesday.setWorkout(cardio);
        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        week.add(thursday);
        week.add(friday);
        week.add(saturday);
        week.add(sunday);
        myPlan = new WorkoutPlan("my plan", week);
    }


    @Test
    public void WorkoutPlanConstructorTest(){
        myPlan = new WorkoutPlan("my plan", week);
        assertEquals("my plan", myPlan.getName());
        assertEquals(monday, myPlan.getDay("monday"));
        assertEquals(tuesday, myPlan.getDay("tuesday"));
        assertEquals(wednesday, myPlan.getDay("wednesday"));
        assertEquals(thursday, myPlan.getDay("thursday"));
        assertEquals(friday, myPlan.getDay("friday"));
        assertEquals(saturday, myPlan.getDay("saturday"));
        assertEquals(sunday, myPlan.getDay("sunday"));
        assertEquals(7, myPlan.getDays().size());



    }

    @Test
    public void getDayInvalidTest() {
        Day day = myPlan.getDay("foo");
        assertEquals(null, day);
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

    @Test
    public void WorkoutPlanToJsonTest() {
        JSONObject json = myPlan.toJson();

        assertEquals("my plan", json.get("name"));
        JSONArray jsonArray = (JSONArray) json.get("days");
        assertEquals(7,jsonArray.length());
    }




}
