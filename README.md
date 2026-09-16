# Smart Expense & Budget Manager

## 1. Overview of the Project

Smart Expense & Budget Manager is a Java-based command-line application that helps users manage their personal income, expenses, and budgets.

The application allows users to record transactions, create budgets for different categories, view financial summaries, and analyze their expenses. The project uses object-oriented programming concepts and stores data in text files for simple and persistent storage.

## 2. Features

- Add income and expense transactions
- View all transactions
- Delete transactions
- Create budgets for expense categories
- View available budgets
- Track spending against budgets
- Display total income and expenses
- Calculate current balance
- Generate category-wise expense reports
- Find the highest expense
- Search transactions by category
- Validate user input
- Store transaction and budget data in text files

## 3. Technologies / Tools Used

- **Programming Language:** Java
- **JDK:** JDK 17 or above
- **Interface:** Command Line Interface (CLI)
- **Data Storage:** Text files
- **Java Concepts:** Classes, Objects, Encapsulation, Inheritance concepts, ArrayList, HashMap, Exception Handling
- **Java API:** Java Time API (`LocalDate`)
- **Development Tool:** Visual Studio Code / IntelliJ IDEA / Eclipse
- **Version Control:** Git and GitHub

## 4. Steps to Install & Run the Project

### Requirements

Install **JDK 17 or above** on your computer.

Check whether Java is installed:

    java -version

    javac -version

### Download the Project

Clone the GitHub repository:

    git clone https://github.com/ruchichabharti/Java-Project-Vityarthi.git

Move into the project folder:

    cd Java-Project-Vityarthi

### Compile the Project

For Windows:

    javac -d out src\Main.java src\model\*.java src\service\*.java src\repository\*.java src\util\*.java

For Linux/macOS:

    javac -d out src/Main.java src/model/*.java src/service/*.java src/repository/*.java src/util/*.java

### Run the Project

After successful compilation, run:

    java -cp out Main

The main menu will appear on the command line.

## 5. Instructions for Testing

Run the program using the steps given above and test each feature from the main menu.

### Test 1: Add Transaction

1. Select **Add Transaction**.
2. Enter the transaction type as Income or Expense.
3. Enter the amount.
4. Enter the category.
5. Enter the description.
6. Enter the date in `YYYY-MM-DD` format.
7. Check that the transaction is added successfully.

### Test 2: View Transactions

1. Select **View Transactions**.
2. Verify that all previously added transactions are displayed correctly.

### Test 3: Delete Transaction

1. Select **Delete Transaction**.
2. Enter the ID of an existing transaction.
3. Verify that the transaction is removed.

### Test 4: Create Budget

1. Select **Create Budget**.
2. Enter an expense category.
3. Enter the budget limit.
4. Verify that the budget is created successfully.

### Test 5: Financial Summary

1. Add some income and expense transactions.
2. Select **Financial Summary**.
3. Verify the displayed total income, total expenses, and balance.

### Test 6: Category Report

1. Add expenses under different categories.
2. Select **Category Report**.
3. Verify that expenses are correctly grouped by category.

### Test 7: Highest Expense

1. Add multiple expense transactions with different amounts.
2. Select **Highest Expense**.
3. Verify that the transaction with the highest expense amount is displayed.

### Test 8: Search by Category

1. Add transactions with different categories.
2. Select **Search by Category**.
3. Enter a category.
4. Verify that transactions belonging to that category are displayed.

### Test 9: Input Validation

Enter invalid values such as:
- Negative amount
- Zero amount
- Empty category
- Invalid date

Verify that the program displays an appropriate error message and asks for valid input.
