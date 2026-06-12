# Expense Tracker

A Java console-based expense tracking application that allows users to manage daily expenses from the terminal.

## Features

* Add Expense
* View Expenses (Formatted Table)
* Update Expense
* Delete Expense
* Search Expenses by Category
* Category-wise Expense Summary
* Calculate Total Spending
* Sort Expenses by Amount
* Sort Expenses by Date
* CSV File Persistence
* Automatic Data Loading on Startup
* Auto-generated Expense IDs
* Input Validation
* Cancel Operations using `menu`

## Technologies Used

* Java
* OOP (Classes & Objects)
* ArrayList
* HashMap
* File I/O
* CSV Storage
* LocalDate
* Collections Framework

## Project Structure

```text
ExpenseTracker/
├── Main.java
├── Expense.java
├── ExpenseManager.java
└── expenses.csv
```

## How to Run

Compile:

```bash
javac Main.java Expense.java ExpenseManager.java
```

Run:

```bash
java Main
```

## Sample Features

### Search by Category

```text
Enter category:
food
```

Displays all expenses belonging to the "food" category.

### Category Summary

```text
Category        Total Expense
--------------------------------
food            760.00
travel          294.00
cab             450.00
```

### Sort Expenses

* Sort by Amount
* Sort by Date

## Future Improvements

* Expense Statistics
* Monthly Budget Tracking
* Date Range Filtering
* Export Reports
* SQLite Database Integration
* GUI Version (JavaFX/Swing)

## Author

Built as a learning project to practice Java, OOP, collections, file handling, and software development fundamentals.
