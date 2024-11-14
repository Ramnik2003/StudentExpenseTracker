package ui;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Student Expense Tracker");
        // new ExpenseTrackerApp();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExpenseTrackerGUI();
            }
        });
        try {
            new ExpenseTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        
    }


}
