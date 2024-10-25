package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println("Welcome to Student Expense Tracker");
        // new ExpenseTrackerApp();
        try {
            new ExpenseTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        
    }


}
