package com.tinkoff.edu.app.service;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

public class BasicLoanCalcService implements LoanCalcService {
    private LoanCalcRepository loanCalcRepository;

    /**
     * Constructor DI
     * @param loanCalcRepository
     */
    public BasicLoanCalcService(LoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    /**
     * Loan calculation
     */
    public LoanResponse createRequest(LoanRequest request, int requestId) {
        return loanCalcRepository.save(request, requestId);
    }
}
