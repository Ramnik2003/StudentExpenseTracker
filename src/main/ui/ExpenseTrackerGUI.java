package ui;

import javax.swing.*;
import java.awt.*;

public class ExpenseTrackerGUI {

    private JFrame frame;
    private JPanel titlePanel;
    private JPanel inputPanel;
    private JLabel title;
    
    public ExpenseTrackerGUI() {
        initialize();
    }

    //REFERENCE: used "Java Swing Programming", by "Java Code Junkie" https://www.youtube.com/watch?v=4BRUmU-ETRk&list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U&index=2
    public void initialize() {
        makeJFrame();
        makeJPanel();
        makeButtonPanel();
        takeInput();
    }

    private void makeJFrame() {
        frame = new JFrame();
        this.frame.setTitle("StudentExpenseTracker");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(500,400);
        this.frame.setVisible(true);
    }

    private void makeJPanel() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(3, 2));
        titlePanel.setBackground(Color.GRAY);
        frame.add(titlePanel);

        titlePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.add(titlePanel, BorderLayout.NORTH);

        title = new JLabel("Student Expense Tracker");
        titlePanel.add(title);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Sans-serif", Font.BOLD, 36));

    }

    private void takeInput() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(10, 2));
        inputPanel.setBackground(Color.WHITE);
        frame.add(inputPanel);

        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.add(titlePanel, BorderLayout.NORTH);

        JLabel amount = new JLabel("Amount:$ ");
        JLabel category = new JLabel("Category: ");
        JLabel date = new JLabel("Date(YYYY-MM-DD): ");
        JTextField amountField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField dateField = new JTextField();


        inputPanel.add(amount);
        inputPanel.add(amountField);

        inputPanel.add(category);
        inputPanel.add(categoryField);

        inputPanel.add(date);
        inputPanel.add(dateField);
    }

    private void makeButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(4,2,5,5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addButton = new JButton("Add Expense");
        JButton saveButton = new JButton("Save User Data");
        JButton loadButton = new JButton("Load Data");
        JButton viewDailyButton = new JButton("View Daily Expenses");
        JButton viewMonthlyButton = new JButton("View Monthly Expenses");
        JButton viewDailyByCatgoryButton = new JButton("View Daily Expenses By Category");
        JButton viewMonthlyByCategoryButton = new JButton("View Monthly Expenses By Category");

        buttonPanel.add(addButton);
        buttonPanel.add(viewDailyButton);
        buttonPanel.add(viewMonthlyButton);
        buttonPanel.add(viewDailyByCatgoryButton);
        buttonPanel.add(viewMonthlyByCategoryButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

    }
}
