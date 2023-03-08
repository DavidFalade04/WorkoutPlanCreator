package persistance;

import model.Day;
import model.Exercise;
import model.Workout;
import model.WorkoutPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//note: template gotten from JsonSerializationDemo
public class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    List<Day> days;
    List<Exercise> exercises;

    @BeforeEach
    private void setup() throws FileNotFoundException {

        List <String> dayNames = new ArrayList<>();
        makeExercises();
        dayNames.add("monday");
        dayNames.add("tuesday");
        dayNames.add("wednesday");
        dayNames.add("thursday");
        dayNames.add("friday");
        dayNames.add("saturday");
        dayNames.add("sunday");

        days = new ArrayList<>();
        for (String dayName : dayNames) {
            Day day = new Day(dayName);
            Workout workout = new Workout("get big 101");
            exercises = makeExercises();
            workout.add(exercises.get(0));
            workout.add(exercises.get(1));
            workout.add(exercises.get(2));
            day.setWorkout(workout);
            days.add(day);


        }

        JsonWriter writer = new JsonWriter("data/JsonData/JsonTestFiles/TestWriterGeneral.json");
        writer.open();
        writer.close();
    }

    private List<Exercise> makeExercises() {
        exercises = new ArrayList<>();
        Exercise e1 = new Exercise("crunch");
        e1.setPr(6);
        e1.setReps(3);
        e1.setSets(4);
        Exercise e2 = new Exercise("hula");
        e2.setPr(8);
        e2.setReps(99);
        e2.setSets(1);
        Exercise e3 = new Exercise("pull-ups");
        e3.setPr(7);
        e3.setReps(2);
        e3.setSets(5);

        exercises.add(e1);
        exercises.add(e2);
        exercises.add(e3);

        return exercises;
    }

    @Test
    void testWriterInvalidFile() {
        try {
            WorkoutPlan wp = new WorkoutPlan("ultimate workoutPlan!", days);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    /*@Test
    void testWriterEmptyWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numThingies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }*/

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkoutPlan wp = new WorkoutPlan("My workout plan", days);
            JsonWriter writer = new JsonWriter("data/JsonData/JsonTestFiles/TestWriterGeneral.json");
            writer.open();
            writer.write(wp,"");
            writer.close();

            JsonReader reader = new JsonReader("data/JsonData/JsonTestFiles/TestWriterGeneral.json");
            List<WorkoutPlan> workoutPlans = reader.read();
            WorkoutPlan readWorkoutPlan = workoutPlans.get(0);
            assertEquals("My workout plan", readWorkoutPlan.getName());
            for (Day d: days) {
                Day day = readWorkoutPlan.getDay(d.getName());
                Workout workout = day.getWorkout();
                assertEquals("get big 101", workout.getWorkoutGoal());
                Exercise e1 =  workout.getExercises().get(0);
                Exercise e2 =  workout.getExercises().get(0);
                Exercise e3 =  workout.getExercises().get(0);

                assertEquals(3, workout.getExercises().size());

                assertEquals("crunch",e1.getName());
                assertEquals(6, e1.getPr());
                assertEquals(3, e1.getReps());
                assertEquals(4,e1.getSets());
            }


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void WriterMultiGeneralWorkroom() {

        try {
            WorkoutPlan wp = new WorkoutPlan("My workout plan", days);
            setup();
            WorkoutPlan wp2 = new WorkoutPlan("My other workout plan", days);
            JsonWriter writer = new JsonWriter("data/JsonData/JsonTestFiles/TestWriterGeneral.json");
            String file = JsonReader.readFile("data/JsonData/JsonTestFiles/TestWriterGeneral.json");
            writer.open();
            writer.write(wp,file);
            writer.close();
            file = JsonReader.readFile("data/JsonData/JsonTestFiles/TestWriterGeneral.json");
            writer.open();
            writer.write(wp2,file);
            writer.close();

            JsonReader reader = new JsonReader("data/JsonData/JsonTestFiles/TestWriterGeneral.json");
            List<WorkoutPlan> workoutPlans = reader.read();
            WorkoutPlan readWorkoutPlan1 = workoutPlans.get(0);
            WorkoutPlan readWorkoutPlan2 = workoutPlans.get(1);

            assertEquals("My workout plan", readWorkoutPlan1.getName());
            assertEquals("My other workout plan",readWorkoutPlan2.getName());
            for (Day d: days) {
                Day day = readWorkoutPlan1.getDay(d.getName());
                Workout workout = day.getWorkout();
                assertEquals("get big 101", workout.getWorkoutGoal());
                Exercise e1 =  workout.getExercises().get(0);
                Exercise e2 =  workout.getExercises().get(0);
                Exercise e3 =  workout.getExercises().get(0);

                assertEquals(3, workout.getExercises().size());

                assertEquals("crunch",e1.getName());
                assertEquals(6, e1.getPr());
                assertEquals(3, e1.getReps());
                assertEquals(4,e1.getSets());
            }


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
