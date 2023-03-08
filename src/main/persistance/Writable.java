package persistance;

import org.json.JSONObject;

//note: Class gotten from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
