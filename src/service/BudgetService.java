package service;

import model.Budget;
import repository.FileRepository;

import java.util.List;

public class BudgetService {

    private List<Budget> budgets;
    private FileRepository repository;

    public BudgetService(FileRepository repository) {

        this.repository = repository;
        this.budgets = repository.loadBudgets();
    }

    public void addBudget(String category, double limit) {

        for (Budget budget : budgets) {

            if (budget.getCategory()
                    .equalsIgnoreCase(category)) {

                System.out.println(
                        "Budget already exists for this category."
                );

                return;
            }
        }

        Budget budget = new Budget(category, limit);

        budgets.add(budget);

        repository.saveBudgets(budgets);

        System.out.println("Budget created successfully.");
    }

    public void updateSpending(String category, double amount) {

        for (Budget budget : budgets) {

            if (budget.getCategory()
                    .equalsIgnoreCase(category)) {

                budget.addSpent(amount);

                repository.saveBudgets(budgets);

                if (budget.isExceeded()) {

                    System.out.println(
                            "WARNING: Budget exceeded for "
                                    + category
                    );

                } else if (budget.getRemaining()
                        <= budget.getLimit() * 0.10) {

                    System.out.println(
                            "WARNING: Only 10% of budget remains."
                    );
                }

                return;
            }
        }
    }

    public void showBudgets() {

        if (budgets.isEmpty()) {

            System.out.println("No budgets created.");

            return;
        }

        System.out.println("\n========== BUDGETS ==========");

        for (Budget budget : budgets) {

            System.out.println(budget);

            if (budget.isExceeded()) {

                System.out.println("Status: EXCEEDED");

            } else {

                System.out.println("Status: Within budget");
            }

            System.out.println("-----------------------------");
        }
    }

    public List<Budget> getBudgets() {

        return budgets;
    }
}