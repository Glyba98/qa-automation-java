package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;

public interface LoanCalcService {
    LoanResponse createRequest(LoanRequest request, int requestId);
}
