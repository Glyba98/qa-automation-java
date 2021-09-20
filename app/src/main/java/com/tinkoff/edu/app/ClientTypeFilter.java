package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.ClientType;

import java.util.function.Predicate;

public class ClientTypeFilter implements Predicate<LoanResponse> {

    @Override
    public boolean test(LoanResponse response) {
        return response.getRequest().getType().equals(ClientType.OOO);
    }
}
