# Student Expence Tracker

## Work of the Application:
This application will help students manage their **expenses**. The application will be especially for students so the features that it will have will be highly in accordance with a student's needs. For example, generally student expenses include, groceries, rent, skincare, stationary, commuting, snacks, coffee etc. So students will be able to input the expense in different categories and at the end the user will be able to see a **list** of daily and montly expenses. It will store the data and will provide user with the graphical representation of the expenses made in various categories. Thus, by tracking their expenses students will be able to better manage their money so that tehy could remain under budget and can invest that money in something profitable. 

## User of the Application:
High school or university going students who want to keep a track on their expenses could use this application. It is particularly designed for *student needs* as the categories will align to student needs more closely.

## Project's interest to me:
I am also a university student and that too a commuter, so sometimes I find myself *spending on the unwanted things* througout the month and running out of money towards the end. So I believe with this tracker I'll be able to **manage** my expenses better and save some bit of money.


# User Stories:

- As a user, I want to be able to add an expense to the list for that day. 
- As a user, I want to able to view a list of my daily expenses.
- As a user, I want to able to divide my daily expenses into differnet categories.
- As a user, I want to able to see a combined list of the total daily and monthly expenses, divided into differnet categories.
- As a user, I want to be able to save my expenses to file (if I so choose).
- As a user, I want to be able to be able to load my expenses from file (if I so choose).

# Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by inputting data in the text fields and then clicking **Add Expense**.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking **Daily Expense** button. It shows the output in the output text area.
- You can save the state of my application by clicking **Save Expense Button**. 
- You can reload the state of my application by clicking **Load Expense** button.
- You can locate my visual compoenent at the left corner of the frame.

## Phase 4: Task 2:

- New expense of amount 24.0 of category Meal  on date 2024-10-10added
- New expense of amount 25.0 of category milk  on date 2024-10-10added
- New expense of amount 35.0 of category fashion  on date 2024-11-11added
- New expense of amount 4.0 of category Coffee  on date 2024-10-11added
- New expense of amount 40.0 of category Movies  on date 2024-11-12added
- Saves data
- New expense of amount 12.0 of category fjinre  on date 2024-11-11added
- Data loaded from the saved list
- All Expenses on 2024-11-11 added
- All the expenses in the month of OCTOBER added
- All expenses on 2024-10-10 added based on category:coffee 
- All the expenses in the month ofOCTOBER added based on category: milk
- Saves data



## Phase 4:  Task 3:
 # If I have more time:
 If I have more time I'd like to add few changes with respect to refactoring. Currently my "ExpenseOperations" class is applying diffferent methods on the list. It takes inout from "ElementExpense" class and adds it to the list. Then according to the methods it filters out the elements from the list. I'd like to divide it's functionality into different classes rather than methods. 
 Also, I'd like to divide the functinality of ExpenseTrackerGUI class into separate classes as well. I tried to divide it into panels but I think reading through the code will be much easier if I separate them into classes. 
 Moreover, as we learned in D4, I can also refactor my model package into classes like composite, component and leaf. In my case leaf could be a single expense that implements teh component, say ComponentExpense. And composite could be ExpenseOperations that also implements ComponentExpense interface.

# References:
- JsonSerialization Demo
  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

- TellerApp 
  https://github.students.cs.ubc.ca/CPSC210/TellerApp 

- FlashCard Reviewer Lab
  
- .filter() method usage referenced from StackOverflow:
    https://stackoverflow.com/questions/71888924/how-to-filter-element-from-a-list-using-java-8-streams
- LocalDate class usage referenced from Oracle:
     https://docs.oracle.com/javase/8/docs/api/?java/time/LocalDate.html
- .filter() and Local Date testing referenced from StackOverflow:
    https://stackoverflow.com/questions/69439833/how-to-filter-an-array-of-objects-by-month-and-year-java

- Exit Listener :
   https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed



    





 