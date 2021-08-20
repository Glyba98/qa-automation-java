package com.tinkoff.edu.app;


public class LoanCalcService {
    private LoanCalcRepository loanCalcRepository = new LoanCalcRepository();
    /**
     * Loan calculation
     */
    public LoanResponse createRequest(LoanRequest request) {
        return loanCalcRepository.save(request);
    }
}
