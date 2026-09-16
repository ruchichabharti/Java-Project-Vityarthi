import model.Transaction;
import repository.FileRepository;
import service.BudgetService;
import service.ReportService;
import service.TransactionService;
import util.DateUtil;
import util.InputValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static TransactionService transactionService;
    private static BudgetService budgetService;
    private static ReportService reportService;

    public static void main(String[] args) {

        FileRepository repository = new FileRepository();

        transactionService =
                new TransactionService(repository);

        budgetService =
                new BudgetService(repository);

        reportService =
                new ReportService(transactionService);

        System.out.println("==========================================");
        System.out.println("     SMART EXPENSE & BUDGET MANAGER");
        System.out.println("==========================================");

        boolean running = true;

        while (running) {

            showMenu();

            int choice =
                    InputValidator.getPositiveInt(
                            scanner,
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    addTransaction();
                    break;

                case 2:
                    viewTransactions();
                    break;

                case 3:
                    deleteTransaction();
                    break;

                case 4:
                    createBudget();
                    break;

                case 5:
                    budgetService.showBudgets();
                    break;

                case 6:
                    reportService.showSummary();
                    break;

                case 7:
                    reportService.categoryReport();
                    break;

                case 8:
                    reportService.highestExpense();
                    break;

                case 9:
                    searchCategory();
                    break;

                case 10:
                    running = false;
                    System.out.println(
                            "Thank you for using Smart Expense Manager!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }

    private static void showMenu() {

        System.out.println("\n============== MAIN MENU ==============");

        System.out.println("1. Add Transaction");
        System.out.println("2. View Transactions");
        System.out.println("3. Delete Transaction");
        System.out.println("4. Create Budget");
        System.out.println("5. View Budgets");
        System.out.println("6. Financial Summary");
        System.out.println("7. Category Report");
        System.out.println("8. Highest Expense");
        System.out.println("9. Search by Category");
        System.out.println("10. Exit");

        System.out.println("========================================");
    }

    private static void addTransaction() {

        System.out.println("\n========== ADD TRANSACTION ==========");

        System.out.println("1. Income");
        System.out.println("2. Expense");

        int typeChoice =
                InputValidator.getPositiveInt(
                        scanner,
                        "Select type: "
                );

        String type;

        if (typeChoice == 1) {
            type = "Income";
        } else if (typeChoice == 2) {
            type = "Expense";
        } else {
            System.out.println("Invalid type.");
            return;
        }

        double amount =
                InputValidator.getPositiveDouble(
                        scanner,
                        "Enter amount: ₹"
                );

        String category =
                InputValidator.getNonEmptyString(
                        scanner,
                        "Enter category: "
                );

        String description =
                InputValidator.getNonEmptyString(
                        scanner,
                        "Enter description: "
                );

        LocalDate date = DateUtil.getDate(scanner);

        int id = generateId();

        Transaction transaction =
                new Transaction(
                        id,
                        type,
                        amount,
                        category,
                        description,
                        date
                );

        transactionService.addTransaction(transaction);

        if (type.equalsIgnoreCase("Expense")) {

            budgetService.updateSpending(
                    category,
                    amount
            );
        }
    }

    private static int generateId() {

        int maxId = 0;

        for (Transaction transaction
                : transactionService.getTransactions()) {

            if (transaction.getId() > maxId) {
                maxId = transaction.getId();
            }
        }

        return maxId + 1;
    }

    private static void viewTransactions() {

        List<Transaction> transactions =
                transactionService.getTransactions();

        System.out.println(
                "\n========== ALL TRANSACTIONS =========="
        );

        if (transactions.isEmpty()) {

            System.out.println("No transactions available.");

            return;
        }

        for (Transaction transaction : transactions) {

            System.out.println(transaction);
        }

        System.out.println(
                "======================================"
        );
    }

    private static void deleteTransaction() {

        int id =
                InputValidator.getPositiveInt(
                        scanner,
                        "Enter transaction ID to delete: "
                );

        transactionService.deleteTransaction(id);
    }

    private static void createBudget() {

        System.out.println("\n========== CREATE BUDGET ==========");

        String category =
                InputValidator.getNonEmptyString(
                        scanner,
                        "Enter category: "
                );

        double limit =
                InputValidator.getPositiveDouble(
                        scanner,
                        "Enter budget limit: ₹"
                );

        budgetService.addBudget(category, limit);
    }

    private static void searchCategory() {

        String category =
                InputValidator.getNonEmptyString(
                        scanner,
                        "Enter category to search: "
                );

        List<Transaction> results =
                transactionService.searchByCategory(category);

        System.out.println(
                "\n========== SEARCH RESULTS =========="
        );

        if (results.isEmpty()) {

            System.out.println(
                    "No transactions found."
            );

        } else {

            for (Transaction transaction : results) {

                System.out.println(transaction);
            }
        }
    }
}