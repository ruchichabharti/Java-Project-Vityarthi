package service;

import model.Transaction;
import repository.FileRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private List<Transaction> transactions;
    private FileRepository repository;

    public TransactionService(FileRepository repository) {

        this.repository = repository;
        this.transactions = repository.loadTransactions();
    }

    public void addTransaction(Transaction transaction) {

        transactions.add(transaction);
        repository.saveTransaction(transaction);

        System.out.println("Transaction added successfully.");
    }

    public List<Transaction> getTransactions() {

        return transactions;
    }

    public double getTotalIncome() {

        double total = 0;

        for (Transaction transaction : transactions) {

            if (transaction.getType().equalsIgnoreCase("income")) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    public double getTotalExpense() {

        double total = 0;

        for (Transaction transaction : transactions) {

            if (transaction.getType().equalsIgnoreCase("expense")) {
                total += transaction.getAmount();
            }
        }

        return total;
    }

    public double getCategoryExpense(String category) {

        double total = 0;

        for (Transaction transaction : transactions) {

            if (transaction.getType().equalsIgnoreCase("expense")
                    && transaction.getCategory()
                    .equalsIgnoreCase(category)) {

                total += transaction.getAmount();
            }
        }

        return total;
    }

    public double getBalance() {

        return getTotalIncome() - getTotalExpense();
    }

    public void deleteTransaction(int id) {

        Transaction found = null;

        for (Transaction transaction : transactions) {

            if (transaction.getId() == id) {
                found = transaction;
                break;
            }
        }

        if (found != null) {

            transactions.remove(found);

            rewriteTransactions();

            System.out.println("Transaction deleted.");

        } else {

            System.out.println("Transaction not found.");
        }
    }

    private void rewriteTransactions() {

        // Recreate the transaction file
        try {

            java.io.FileWriter writer =
                    new java.io.FileWriter("data/transactions.txt");

            for (Transaction transaction : transactions) {

                writer.write(transaction.toFileString());
                writer.write(System.lineSeparator());
            }

            writer.close();

        } catch (Exception e) {

            System.out.println("Error updating transaction file.");
        }
    }

    public List<Transaction> searchByCategory(String category) {

        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getCategory()
                    .equalsIgnoreCase(category)) {

                result.add(transaction);
            }
        }

        return result;
    }

    public List<Transaction> getTransactionsByDate(LocalDate date) {

        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {

            if (transaction.getDate().equals(date)) {
                result.add(transaction);
            }
        }

        return result;
    }
}