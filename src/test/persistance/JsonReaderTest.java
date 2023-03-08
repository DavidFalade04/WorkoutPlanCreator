package persistance;

import model.Day;
import model.WorkoutPlan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//note: template gotten from JsonSerializationDemo
public class JsonReaderTest {
List <String> dayNames;


        @Test
        void testReaderNonExistentFile() {
            JsonReader reader = new JsonReader("./data/noSuchFile.json");
            try {
                List <WorkoutPlan> wr = reader.read();
                fail("IOException expected");
            } catch (IOException e) {
                // pass
            }
        }

        @Test
        void testReaderEmptyWorkoutPlan() {
            JsonReader reader = new JsonReader("data/JsonData/JsonTestFiles/TestNoWorkoutPlans.json");
            try {
                List<WorkoutPlan> workoutPlans = reader.read();
                assertEquals(0, workoutPlans.size());
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }

        @Test
        void testReaderGeneralWorkoutPlan() {
            initDefaultDays();
            JsonReader reader = new JsonReader("data/JsonData/JsonTestFiles/TestWorkoutPlan.json");
            try {
                List<WorkoutPlan> plans = reader.read();
                WorkoutPlan wp = plans.get(0);
                assertEquals("David's Workout", wp.getName());
                List<Day> days = new ArrayList<>();
                for (String d : dayNames) {
                    Day day = wp.getDay(d);
                    days.add(day);
                }
                assertEquals(7,days.size());

            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }

    @Test
    void testReaderGeneralNullWorkout() {
        initDefaultDays();

        JsonReader reader = new JsonReader("data/JsonData/JsonTestFiles/TestNullWorkout.json");
        try {
            List<WorkoutPlan> plans = reader.read();
            WorkoutPlan wp = plans.get(0);
            assertEquals("David's Workout", wp.getName());
            List<Day> days = new ArrayList<>();
            for (String d : dayNames) {
                Day day = wp.getDay(d);
                assertEquals(null, day.getWorkout());
                days.add(day);
            }
            assertEquals(7,days.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadEmptyWorkout() {
        initDefaultDays();

        JsonReader reader = new JsonReader("data/JsonData/JsonTestFiles/TestReadEmptyFile.json");
        try {
            List<WorkoutPlan> plans = reader.read();
            assertTrue(plans.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



    //Modifies: this
    //EFFECTS: creates days of the week and initializes names of day
    private void initDefaultDays() {
        dayNames = new ArrayList<>();
        dayNames.add("monday");
        dayNames.add("tuesday");
        dayNames.add("wednesday");
        dayNames.add("thursday");
        dayNames.add("friday");
        dayNames.add("saturday");
        dayNames.add("sunday");
    }

}
