package com.tinkoff.edu.app;

public class LoanCalcService {
    /**
     * Loan calculation
     */
    public static int createRequest() {
        return LoanCalcRepository.save();
    }
}
