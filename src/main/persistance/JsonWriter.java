package persistance;

import model.WorkoutPlan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import java.io.*;
import java.sql.Savepoint;

//Note: file structure derived from JsonSerializationDemo
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workoutPlan to file
    public void write(WorkoutPlan wp, String file) throws IOException {
        JSONObject json = wp.toJson();
        if (file.length() == 0) {
            saveToFile("[");
            saveToFile(json.toString(TAB));
            saveToFile("]");
        } else {
            JSONArray jsonArray = new JSONArray(file);
            file = jsonArray.toString(TAB);
            file = file.substring(0, file.length() - 1);
            saveToFile(file);
            saveToFile(",");
            saveToFile(json.toString(TAB));
            saveToFile("]");
        }

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
