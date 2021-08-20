package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcRepository.save;

public class LoanCalcService {
    /**
     * Loan calculation
     */
    public static LoanResponse createRequest(LoanRequest request) {
        return save(request);
    }
}
