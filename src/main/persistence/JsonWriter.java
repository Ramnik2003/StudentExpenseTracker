package persistence;

import model.ElementExpense;
import model.ExpensesOperations;
import org.json.JSONObject;

import java.io.FileNotFoundException;

import org.json.JSONArray;

//Represents a writer that writes JSON representation of ExpenceOperation to file
public class JsonWriter {

    //EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        //stub
    }

    //REQUIRES: destination file should be open for writing
    //MODIFIES: this
    //EFFECTS: opens writer 
    public void open() throws FileNotFoundException {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of ExpenseOpertions to file 
    public void write(ExpensesOperations et) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: closes the writer
    public void close() {
        //stub
    }

}
