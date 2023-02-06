package model;

public class Exercise {
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
    public void newPr(int pr) {
        this.pr = pr;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
