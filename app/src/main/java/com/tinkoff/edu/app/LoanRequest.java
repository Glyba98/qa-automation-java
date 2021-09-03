package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.ClientType;

import java.math.BigDecimal;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private final int months;
    private final BigDecimal amount;
    private final ClientType type;

    public LoanRequest(int months, BigDecimal amount, ClientType type) {
        this.months = months;
        this.amount = amount;
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getMonths() {
        return months;
    }

    public ClientType getType() {
        return type;
    }

    public String toString() {
        return "RQ: {"
                + this.getType() + ", "
                + this.getAmount() + " for "
                + this.getMonths() + "}";
    }
}

