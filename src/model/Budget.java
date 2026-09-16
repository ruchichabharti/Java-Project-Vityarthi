package model;

public class Budget {

    private String category;
    private double limit;
    private double spent;

    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
        this.spent = 0;
    }

    public Budget(String category, double limit, double spent) {
        this.category = category;
        this.limit = limit;
        this.spent = spent;
    }

    public String getCategory() {
        return category;
    }

    public double getLimit() {
        return limit;
    }

    public double getSpent() {
        return spent;
    }

    public void addSpent(double amount) {
        spent += amount;
    }

    public double getRemaining() {
        return limit - spent;
    }

    public boolean isExceeded() {
        return spent > limit;
    }

    public String toFileString() {
        return category + "|" + limit + "|" + spent;
    }

    @Override
    public String toString() {
        return "Category: " + category +
                " | Budget: ₹" + limit +
                " | Spent: ₹" + spent +
                " | Remaining: ₹" + getRemaining();
    }
}