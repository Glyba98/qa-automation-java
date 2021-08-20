package com.tinkoff.edu.app;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private int months;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
