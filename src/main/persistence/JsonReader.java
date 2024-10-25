package persistence;

import model.ElementExpense;
import model.ExpensesOperations;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

//Represents a reader that reads ExpenseOperations from JSON data stored in file
public class JsonReader {
    private String source;
    private String jsonData;

    //EFFECTS: constructs reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads ExpenseOperations from file and returns it
    public ExpensesOperations read() throws IOException {
        jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpensesOperations(jsonObject);

    }

    //EFFECTS: reads the source file and returns it 
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();

    }

    // EFFECTS: parses ExpenseOpereations from JSON object and returns it
    private ExpensesOperations parseExpensesOperations(JSONObject jsonObject) {
        ExpensesOperations expenses = new ExpensesOperations();
        addExpenses(expenses, jsonObject);
        return expenses;
    }

    // MODIFIES: expenses
    // EFFECTS: parses expenses from JSON object and adds them to ExpenseOpereations
    private void addExpenses(ExpensesOperations expenses, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(expenses, nextExpense);
        }
    }

    // MODIFIES: expenses
    // EFFECTS: parses expense from JSON object and adds it to ExpensesOperations
    private void addExpense(ExpensesOperations expenses, JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String category = jsonObject.getString("category");
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));      
        ElementExpense expense = new ElementExpense(amount, category, date);
        expenses.addExpense(expense);
    }

}
