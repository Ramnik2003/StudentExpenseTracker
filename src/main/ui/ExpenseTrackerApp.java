//I am refering to TellerApp and FlashCardReviewer lab.

package ui;

import java.util.*;

import model.ElementExpense;
import model.ExpensesOperations;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.time.LocalDate;

import java.io.FileNotFoundException;
import java.io.IOException;


//ExpenseTracker App that allows the user to add expenses and track them
public class ExpenseTrackerApp {
    private static final String JSON_STORE = "./data/expenses.json";
    private Scanner input;
    private ExpensesOperations expenses;
    //private boolean isProgramRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the application
    public ExpenseTrackerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTracker();
    }

    //EFFECTS:process the input from user
    public void runTracker() {
        boolean isProgramRunning = true;

        init();

        while (isProgramRunning) {
            displayMenu();
            String command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                isProgramRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");

    }

    //EFFECTS: initialise the programme
    private void init() {
        expenses = new ExpensesOperations();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    //EFFECTS: shows options, what a user can do using the application
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add expense");
        System.out.println("\tb -> view total daily expenses");
        System.out.println("\td -> View total daily expenses by category");
        System.out.println("\tc -> view total monthly expenses");
        System.out.println("\tm -> View total monthly expenses by category");
        System.out.println("\ts -> save expenses to file");
        System.out.println("\tl -> load expenses from file");
        System.out.println("\tq -> quit");
    }

    
    //MODIFIES: this
    //EFFECTS: processes user commands
    @SuppressWarnings("methodlength") 
    private void processCommand(String command) {
        switch (command) {
            case "a":
                addNewExpense();
                break;
            case "b":
                viewTotalDailyExpenses();
                break;
            case "c":
                viewTotalMonthlyExpenses();
                break;
            case "d":
                viewTotalDailyPerCategory();
                break;
            case "m":
                viewTotalMonthlyPerCategory();
                break;
            case "s":
                saveExpensesOperations();
                break;
            case "l":
                loadExpensesOperations();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }




    //REQUIREES: expense amount>=0
    //MODIFIES: this
    //EFFECTS: adds expense to the list
    private void addNewExpense() {
        System.out.print("Please enter the expense amount: $");
        double amount = input.nextDouble();
        input.nextLine(); 

        System.out.print("Please enter the category: ");
        String category = input.nextLine();

        System.out.print("Please enter the date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(input.nextLine());

        ElementExpense expense = new ElementExpense(amount, category, date);
        expenses.addExpense(expense);
        System.out.println("New expense successfully added!");
    }

    //MODIFIES: this
    //EFFECTS: adds all the expenses on the selected date
    private void viewTotalDailyExpenses() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(input.nextLine());
        double total = expenses.totalDaily(date);
        System.out.println("Total expenses for " + date + ": $" + total);
    }

    //MODIFIES: this
    //EFFECTS: adds all the expenses in the selected month
    private void viewTotalMonthlyExpenses() {
        System.out.print("Enter date (YYYY-MM-DD)(choose any day): ");
        LocalDate date = LocalDate.parse(input.nextLine());

        double total = expenses.totalMonthly(date);
        System.out.println("Total expenses for " + date.getMonthValue() + "/" + date.getYear() + ": $" + total);
    }

    //MODIFIES: this
    //EFFECTS: adds all the expenses of a particular category on a selected day
    public void viewTotalDailyPerCategory() {
        System.out.println("Enter the date (yyyy-MM-dd)(choose any day): ");
        String dateInput = input.nextLine();
        LocalDate date = LocalDate.parse(dateInput);
    
        System.out.println("Enter the category: ");
        String category = input.nextLine();
    
        double total = expenses.totalDailyPerCategory(date, category);
        System.out.println("Total daily expenses for category '" + category + "' on " + date + ": " + total);
    }

    //MODIFIES: this
    //EFFECTS: adds all the expenses of a particular category in the selected month
    public void viewTotalMonthlyPerCategory() {
        System.out.println("Enter the date (yyyy-MM-dd): ");
        String dateInput = input.nextLine();
        LocalDate date = LocalDate.parse(dateInput);
    
        System.out.println("Enter the category: ");
        String category = input.nextLine();
    
        double total = expenses.totalMonthlyPerCategory(date, category);
        System.out.println("Total monthly expenses for category '" + category 
                            + "' in " + date.getYear() + "-" + date.getMonthValue() + ": " + total);
    }

    // EFFECTS: saves the expensesOperations to file
    private void saveExpensesOperations() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenses);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadExpensesOperations() {
        try {
            expenses = jsonReader.read();
            System.out.println("Loaded expenses from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    
}


    


