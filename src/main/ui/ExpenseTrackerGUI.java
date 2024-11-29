package ui;

import javax.swing.*;
import model.ElementExpense;
import model.EventLog;
import model.ExpensesOperations;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Event;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



import java.awt.*;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//creates an ExpenseTrackerGUI that has different panels for inputs and outputs
public class ExpenseTrackerGUI extends JPanel {
    private static final String JSON_STORE = "./data/expenses.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
  
    private ExpensesOperations expensesOperations;
    private ElementExpense expense;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton viewDailyButton;
    private JButton viewMonthlyButton;
    private JButton viewDailyByCatgoryButton;
    private JButton viewMonthlyByCategoryButton;
    private JLabel amountL;
    private JLabel categoryL;
    private JLabel dateL;
    private JTextField amountField;
    private JTextField categoryField;
    private JTextField dateField;
    private JTextArea outputArea;

    private JFrame frame;
    private JPanel titlePanel;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel buttonPanel;
    private JLabel title;

    
    public ExpenseTrackerGUI() {
        expensesOperations = new ExpensesOperations();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        initialize();
    }

    //REFERENCE: used "Java Swing Programming", by "Java Code Junkie" https://www.youtube.com/watch?v=4BRUmU-ETRk&list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U&index=2
    //EFFECTS: calls methods that make up different components of the frame
    public void initialize() {
        makeJFrame();
        makeJPanel();
        takeInput();
        makeButtonPanel();
        giveOutput();
    }

    //EFFECTS: initialises the basic frame for the application
    private void makeJFrame() {
        frame = new JFrame();
        this.frame.setTitle("Student Expense Tracker");
        this.frame.setSize(800,600);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        printEvent();
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout());
    }

    //REFERENCE: https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
    //EFFECTS: prints all the logged events when the application quits
    public void printEvent(){
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                System.out.println(event.getDescription());
        }  
            }
        });
        
    }

    //EFFECTS: makes the panel for the title of the application
    private void makeJPanel() {
        titlePanel = new JPanel();
        titlePanel.setSize(getPreferredSize());
        titlePanel.setLayout(new GridLayout());
        titlePanel.setBackground(Color.GRAY);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.add(titlePanel, BorderLayout.PAGE_START);

        ImageIcon image = new ImageIcon(getClass().getResource("Logo.png"));
        
        Image scaledImage = image.getImage();
        Image setSizeImage = scaledImage.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(setSizeImage));

        imageLabel.setBorder(null);
        title = new JLabel("Student Expense Tracker");
        title.setBorder(null);


        titlePanel.add(imageLabel);
        titlePanel.add(title);
        titlePanel.setBorder(null);

        title.setForeground(Color.WHITE);
        title.setFont(new Font("Sans-serif", Font.BOLD, 36));

    }

    // EFFECTS: Creates Input Panel in that it creates different fields to take inputs from the user 
    private void takeInput() {
        inputPanel = new JPanel();
        inputPanel.setBackground(Color.PINK);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setPreferredSize(new Dimension(300,150));
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        amountField = new JTextField(5);
        categoryField = new JTextField(10);
        dateField = new JTextField(10);

        amountL = new JLabel("Amount:$ ");
        categoryL = new JLabel("Category: ");
        dateL = new JLabel("Date(YYYY-MM-DD): ");

        inputPanel.add(amountL);
        inputPanel.add(amountField);

        inputPanel.add(categoryL);
        inputPanel.add(categoryField);

        inputPanel.add(dateL);
        inputPanel.add(dateField);
        frame.add(inputPanel, BorderLayout.WEST);
        makeButtonPanel();
        
    }

    //EFFECTS: creates Button Panel that creates buttons and enables them according to the users choice
    private void makeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(56,34,89));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(250, 300));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        addButton = new JButton("Add New Expense");
        saveButton = new JButton("Save Expenses");
        loadButton = new JButton("Load Data");
        viewDailyButton = new JButton("View Daily Expenses");
        viewMonthlyButton = new JButton("View Monthly Expenses");
        viewDailyByCatgoryButton = new JButton("View Daily Expenses By Category");
        viewMonthlyByCategoryButton = new JButton("View Monthly Expenses By Category");

        buttonPanel.add(addButton);
        buttonPanel.add(viewDailyButton);
        buttonPanel.add(viewMonthlyButton);
        buttonPanel.add(viewDailyByCatgoryButton);
        buttonPanel.add(viewMonthlyByCategoryButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        buttonFunctioningEnabled();

        frame.add(buttonPanel, BorderLayout.EAST);
    }

    //EFFECTS: enables functioning of the buttons
    private void buttonFunctioningEnabled() {
        addButton.addActionListener(new AddNewExpenseListener());
        saveButton.addActionListener(new SaveDataListener());
        loadButton.addActionListener(new LoadDataListener());
        viewDailyButton.addActionListener(new AddDailyExpenseListener());
        viewMonthlyButton.addActionListener(new AddMonthlyExpenseListener());
        viewDailyByCatgoryButton.addActionListener(new AddDailyExpensePerCategoryListener());
        viewMonthlyByCategoryButton.addActionListener(new AddMonthlyExpensePerCategoryListener());
    }


    //REFRENCE: https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
    //                                  AND
    //          https://stackoverflow.com/questions/34145092/cant-add-jscrollpane-to-jtextarea 
    //EFFECTS: creates output panel and in that creates an output text area that prints out the
    //         result after applying operations
    private void giveOutput() {
        outputPanel = new JPanel();
       
        outputPanel.setBackground(Color.GRAY);
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        outputPanel.setPreferredSize(new Dimension(300,100));
        outputPanel.setLayout(new BorderLayout());
        outputArea = new JTextArea("Output\n", 3, 5);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.CENTER);

    }

    //EFFECTS:resets the text fields to empty
    private void resetTextField() {
        amountField.setText("");
        categoryField.setText("");
        dateField.setText("");
    }


    //ActionListener Overall REFERENCES: Refernce for ActionListener Usage: 
    //           https://stackoverflow.com/questions/44121663/using-actionlistener 
    //           Reference for Arithemetic calculations:
    //           https://stackoverflow.com/questions/62470646/how-to-do-arithmetic-calculation-in-action-listener
    //
    
    //Clicking "Add New Expense" button helps add new expense, ActionListener is used for this
    private class AddNewExpenseListener implements ActionListener {
        @Override
        //REQUIRES: input enteries should be in correct format 
        //MODIFIES: this
        //EFFECTS: adds the typed information to the list expense list
        public void actionPerformed(ActionEvent e) {
            double newAmount = Double.parseDouble(amountField.getText());
            String category = categoryField.getText();
            LocalDate date = LocalDate.parse(dateField.getText());
            expense = new ElementExpense(newAmount, category, date);
            expensesOperations.addExpense(expense);
            resetTextField();
            outputArea.append("Added expense: " + expense.getExpense() + " in category: " 
                               + expense.getCategory() + " on " + expense.getDate() + "\n");
        }
    }

    //Clicking the "Save expense" button allows user to save the input
    private class SaveDataListener implements ActionListener {

        @Override

        //MODIFIES: this
        //EFFECTS: saves the inputed expenses in expense.json
        public void actionPerformed(ActionEvent e) {
            // jsonWriter = new JsonWriter("expense.json");
            try {
                jsonWriter.open();
                jsonWriter.write(expensesOperations);
                jsonWriter.close();
                outputArea.append("Data saved to" + JSON_STORE + "\n");
    
            } catch (FileNotFoundException e1) {
                outputArea.append("Error");
            } finally {
                EventLog.getInstance().logEvent(new Event("Saves data"));

            }

        }

    }

    //Clicking the Load Expense button allows the user to Load the saved file
    private class LoadDataListener implements ActionListener {

        //MODIFIES: this
        //EFFECTS: loads the already saved expenses from "expenses.json"
        @Override
        public void actionPerformed(ActionEvent e) {
            // jsonReader = JsonReader(JSON_STORE);
            try {
                expensesOperations = jsonReader.read();
                outputArea.append("Data loaded from " + JSON_STORE + "\n");
            } catch (IOException e1) {
                outputArea.append("Error");
            } finally {
                EventLog.getInstance().logEvent(new Event("Data loaded from the saved list"));

            }
        }

    }

    //REFERENCE: Reference for new Dialogue Box: https://mkyong.com/swing/java-swing-joptionpane-showinputdialog-example/
    //https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-user-input-with-a-Swing-JOptionPane-example 
    //http://www.java2s.com/Tutorials/Java/java.time/LocalDate/index.htm#google_vignette
    //Clicking this Button allows the user to add all the expenses from a particular date
    //MODIFIES: this
    //EFFECTS: adds all the expenses for the particular date entered
    private class AddDailyExpenseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputDate = JOptionPane.showInputDialog(frame,"Enter the date you want the total expenses from: ");
            if (inputDate != null) {
                LocalDate date = LocalDate.parse(inputDate);
                double total = expensesOperations.totalDaily(date);
                outputArea.setText("Total expenses for " + date + ": $" + total + "\n");
            } else {
                outputArea.setText("Empty");
            }
        }

    }
    

    private class AddMonthlyExpenseListener implements ActionListener {

        
        //MODIFIES: this
        //EFFECTS: adds all the expenses in the particular month, for which the date is inputed 
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputMonth = JOptionPane.showInputDialog(frame,"Enter the Month(Format: YYYY-MM-DD)" 
                                                            + " you want the total expenses from: ");
            if (inputMonth != null) {
                LocalDate date = LocalDate.parse(inputMonth);
                double total = expensesOperations.totalMonthly(date);
                outputArea.setText("Total expenses in " + date.getMonth() 
                                   +  " " + date.getYear() + ": $" + total + "\n");
            } else {
                outputArea.setText("Empty");
            }

        }

    }

    private class AddDailyExpensePerCategoryListener implements ActionListener {

        //MODIFIES: this
        //EFFECTS: adds all the expenses for the inputed date, filtered out based on the inputed category
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputDate = JOptionPane.showInputDialog(frame,"Enter the Date(Format: YYYY-MM-DD)"
                                                            + " you want the total expenses from: ");
            String inputCategory = JOptionPane.showInputDialog(frame,"Enter the category" 
                                                                + " you want the total expenses from: ");

            if (inputDate != null && inputCategory != null) {
                LocalDate date = LocalDate.parse(inputDate);
                double total = expensesOperations.totalDailyPerCategory(date, inputCategory);
                outputArea.setText("Total expenses in " + date + " for " + inputCategory + ": $" + total + "\n");
            } else {
                outputArea.setText("Empty");
            }

        }

    }


    private class AddMonthlyExpensePerCategoryListener implements ActionListener {

        //MODIFIES: this
        //EFFECTS: adds all the expenses in the month for which the date is inputed 
        //         and then filters out based on the category
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputMonth = JOptionPane.showInputDialog(frame,"Enter the Month(Format: YYYY-MM-DD)" 
                                                             + " you want the total expenses from: ");
            String inputCategory = JOptionPane.showInputDialog(frame,"Enter the category you" 
                                                               + " want the total expenses from: ");

            if (inputMonth != null) {
                LocalDate date = LocalDate.parse(inputMonth);
                double total = expensesOperations.totalMonthlyPerCategory(date, inputCategory);
                outputArea.setText("Total expenses in " + date.getMonth() +  " " 
                                   + date.getYear() + "for " + inputCategory + ": $" + total + "\n");
            } else {
                outputArea.setText("Empty");
            }

        }

    
    
    }

    

   


    
    
}
