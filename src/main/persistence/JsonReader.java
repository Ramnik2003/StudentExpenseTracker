package persistence;

import model.ElementExpense;
import model.ExpensesOperations;
import org.json.JSONArray;
import org.json.JSONObject;

//Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    //

}
