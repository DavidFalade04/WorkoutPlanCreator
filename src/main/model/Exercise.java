package model;

import org.json.JSONObject;
import persistance.Writable;

// An exercise with a name, and a number of reps and sets, as well as a Pr
public class Exercise implements Writable {
    private String name;
    private int reps;
    private int sets;
    private int pr;

    // EFFECTS: makes exercise with name and pr, reps and sets to 0
    public Exercise(String name) {
        this.name = name;
        this.pr = 0;
        this.reps = 0;
        this.sets = 0;
    }

    public int getPr() {
        return pr;
    }

    //MODIFIES: this
    //EFFECTS: changes pr
    public void setPr(int pr) {
        this.pr = pr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
        EventLog.getInstance().logEvent(new Event("exercise reps were changed"));
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
        EventLog.getInstance().logEvent(new Event("exercise's sets were changed"));
    }

    @Override
    //EFFECTS: converts Exercise to JsonObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pr", pr);
        json.put("reps", reps);
        json.put("sets", sets);
        return json;
    }
}
