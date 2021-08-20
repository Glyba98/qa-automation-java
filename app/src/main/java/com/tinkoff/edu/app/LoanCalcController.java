package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    private LoanCalcService loanCalcService = new LoanCalcService();
    /**
     * Validates and logs request
     */
    public LoanResponse createRequest(LoanRequest request) {
        log();

        return  loanCalcService.createRequest(request);
    }
}
