package service;

import model.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    private TransactionService transactionService;

    public ReportService(TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    public void showSummary() {

        double income = transactionService.getTotalIncome();
        double expense = transactionService.getTotalExpense();
        double balance = transactionService.getBalance();

        System.out.println("\n========== FINANCIAL SUMMARY ==========");

        System.out.printf("Total Income  : ₹%.2f%n", income);
        System.out.printf("Total Expense : ₹%.2f%n", expense);
        System.out.printf("Balance       : ₹%.2f%n", balance);

        System.out.println("========================================");
    }

    public void categoryReport() {

        Map<String, Double> categoryTotals = new HashMap<>();

        List<Transaction> transactions =
                transactionService.getTransactions();

        for (Transaction transaction : transactions) {

            if (transaction.getType()
                    .equalsIgnoreCase("expense")) {

                String category = transaction.getCategory();

                categoryTotals.put(
                        category,
                        categoryTotals.getOrDefault(category, 0.0)
                                + transaction.getAmount()
                );
            }
        }

        System.out.println(
                "\n========== CATEGORY REPORT =========="
        );

        if (categoryTotals.isEmpty()) {

            System.out.println("No expense data available.");

            return;
        }

        for (Map.Entry<String, Double> entry
                : categoryTotals.entrySet()) {

            System.out.printf(
                    "%-20s ₹%.2f%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }

        System.out.println("=====================================");
    }

    public void highestExpense() {

        Transaction highest = null;

        for (Transaction transaction
                : transactionService.getTransactions()) {

            if (transaction.getType()
                    .equalsIgnoreCase("expense")) {

                if (highest == null ||
                        transaction.getAmount()
                                > highest.getAmount()) {

                    highest = transaction;
                }
            }
        }

        System.out.println("\n========== HIGHEST EXPENSE ==========");

        if (highest == null) {

            System.out.println("No expenses recorded.");

        } else {

            System.out.println(highest);
        }

        System.out.println("=====================================");
    }
}