package repository;

import model.Transaction;
import model.Budget;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {

    private final String transactionFile = "data/transactions.txt";
    private final String budgetFile = "data/budgets.txt";

    public FileRepository() {
        createDataFolder();
    }

    private void createDataFolder() {
        File folder = new File("data");

        if (!folder.exists()) {
            folder.mkdir();
        }

        createFileIfNotExists(transactionFile);
        createFileIfNotExists(budgetFile);
    }

    private void createFileIfNotExists(String path) {
        try {
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void saveTransaction(Transaction transaction) {

        try (FileWriter writer = new FileWriter(transactionFile, true)) {

            writer.write(transaction.toFileString());
            writer.write(System.lineSeparator());

        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    public List<Transaction> loadTransactions() {

        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(transactionFile))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length >= 6) {

                    int id = Integer.parseInt(parts[0]);
                    String type = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String category = parts[3];
                    String description = parts[4];
                    LocalDate date = LocalDate.parse(parts[5]);

                    transactions.add(
                            new Transaction(
                                    id,
                                    type,
                                    amount,
                                    category,
                                    description,
                                    date
                            )
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading transactions.");
        }

        return transactions;
    }

    public void saveBudgets(List<Budget> budgets) {

        try (FileWriter writer = new FileWriter(budgetFile)) {

            for (Budget budget : budgets) {

                writer.write(budget.toFileString());
                writer.write(System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Error saving budgets.");
        }
    }

    public List<Budget> loadBudgets() {

        List<Budget> budgets = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(budgetFile))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length >= 3) {

                    String category = parts[0];
                    double limit = Double.parseDouble(parts[1]);
                    double spent = Double.parseDouble(parts[2]);

                    budgets.add(
                            new Budget(category, limit, spent)
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading budgets.");
        }

        return budgets;
    }
}