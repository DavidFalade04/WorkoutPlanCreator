package persistance;


import model.Day;
import model.Exercise;
import model.Workout;
import model.WorkoutPlan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//Note: large portion from JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads workout plans from file and returns them
    public List<WorkoutPlan> read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);

        List<WorkoutPlan> workoutPlans = new ArrayList<>();

        for (Object json : jsonArray) {
            JSONObject jsonObject = (JSONObject) json;
            WorkoutPlan workoutPlan = readPlan(jsonObject);
            workoutPlans.add(workoutPlan);
        }
        return workoutPlans;

    }

    // EFFECTS: reads workoutPlan from JSONobject returns it;
    // throws IOException if an error occurs reading data from file
    private WorkoutPlan readPlan(JSONObject jsonObject) throws IOException {
        return parseWorkoutPlan(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workoutPlan from JSON object and returns it
    private WorkoutPlan parseWorkoutPlan(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        List<Day> days = parseDays(jsonObject);
        WorkoutPlan wp = new WorkoutPlan(name, days);
        return wp;
    }

    //EFFECTS: parses days from JSON object and returns them in a list
    private List<Day> parseDays(JSONObject jsonObject) {
        List<Day> days = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            days.add(makeDay(nextDay));
        }
        return days;
    }

    //EFFECTS: reconstruct a day from a JSON object
    private Day makeDay(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String status = jsonObject.getString("status");
        JSONObject workoutJson = jsonObject.getJSONObject("workout");
        Workout workout = makeWorkout(workoutJson);
        Day day = new Day(name);

        day.setStatus(status);
        day.setWorkout(workout);

        return day;
    }

    //EFFECTS: reconstruct a workout from a JSON object
    private Workout makeWorkout(JSONObject jsonObject) {
        String workoutGoal = jsonObject.getString("workout goal");
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        List<Exercise> exercises = parseExercises(jsonArray);
        Workout workout = new Workout(workoutGoal);
        for (Exercise e : exercises) {
            workout.add(e);
        }
        return workout;
    }


    //EFFECTS: parses exercises from JSONArray and returns them in a list
    private List<Exercise> parseExercises(JSONArray jsonArray) {
        List<Exercise> exercises = new ArrayList<Exercise>();
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            exercises.add(makeExercise(nextExercise));
        }
        return exercises;
    }

    //EFFECTS: reconstruct exercise from JSON object
    private Exercise makeExercise(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer pr = jsonObject.getInt("pr");
        Integer reps = jsonObject.getInt("reps");
        Integer sets = jsonObject.getInt("sets");
        Exercise exercise = new Exercise(name);
        exercise.setPr(pr);
        exercise.setReps(reps);
        exercise.setSets(sets);

        return exercise;

    }

}
