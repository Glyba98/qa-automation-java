package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;

public interface LoanCalcRepository {
    LoanResponse save(LoanRequest request);
}
