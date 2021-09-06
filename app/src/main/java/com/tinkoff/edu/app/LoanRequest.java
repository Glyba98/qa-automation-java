package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.ClientType;

import java.math.BigDecimal;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private final String fullname;
    private final int months;
    private final BigDecimal amount;
    private final ClientType type;

    public LoanRequest(String fullname, int months, BigDecimal amount, ClientType type) {
        this.fullname = fullname;
        this.months = months;
        this.amount = amount;
        this.type = type;
    }

    public String getFullname() {
        return fullname;
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
                + this.getType() + " "
                + this.getFullname() + ", "
                + this.getAmount() + " for "
                + this.getMonths() + "}";
    }
}

