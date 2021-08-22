package com.tinkoff.edu.app;

public interface LoanCalcRepository {
    LoanResponse save(LoanRequest request);
}
