package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.LoanType;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private final int months;
    private final int amount;
    private final LoanType type;

    public LoanRequest(int months, int amount, LoanType type) {
        this.months = months;
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public int getMonths() {
        return months;
    }

    public LoanType getType() {
        return type;
    }

    public String toString() {
        return "RQ: {"
                + this.getType() + ", "
                + this.getAmount() + " for "
                + this.getMonths() + "}";
    }
}

