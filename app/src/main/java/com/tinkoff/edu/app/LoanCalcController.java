package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    BasicLoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcRepository repo) {
        BasicLoanCalcService loanCalcService = new BasicLoanCalcService(repo);
    }

    /**
     * Validates and logs request
     */
    public LoanResponse createRequest(LoanRequest request) {
        log();

        return  loanCalcService.createRequest(request);
    }
}
