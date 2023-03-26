package ui;

import ui.gui.WorkoutCreatorAppGui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        ;

        System.out.println("0 = gui, 1 = console");
        int answer = input.nextInt();

        if (answer == 0) {
            new WorkoutCreatorAppGui();
        } else {
            try {
                new WorkoutCreatorApp();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
