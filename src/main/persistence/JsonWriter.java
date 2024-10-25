package persistence;

import model.ElementExpense;
import model.ExpensesOperations;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;

//Represents a writer that writes JSON representation of ExpenceOperation to file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //REQUIRES: destination file should be open for writing
    //MODIFIES: this
    //EFFECTS: opens writer 
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of ExpenseOpertions to file 
    public void write(ExpensesOperations et) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonExpenses = new JSONArray();
        
        for (ElementExpense expense : et.getExpensesList()) {
            JSONObject jsonExpense = new JSONObject();
            jsonExpense.put("date", expense.getDate().toString());
            jsonExpense.put("amount", expense.getExpense());
            jsonExpense.put("category", expense.getCategory());
            jsonExpenses.put(jsonExpense);
        }
        
        jsonObject.put("expenses", jsonExpenses);
        writer.print(jsonObject.toString(4));
    }

    //MODIFIES: this
    //EFFECTS: closes the writer
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }

}
