package model;

import java.time.LocalDate;

public class Transaction {

    private int id;
    private String type;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    public Transaction(int id, String type, double amount,
                       String category, String description, LocalDate date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toFileString() {
        return id + "|" + type + "|" + amount + "|" +
               category + "|" + description + "|" + date;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | " + type +
                " | ₹" + amount +
                " | " + category +
                " | " + description +
                " | " + date;
    }
}