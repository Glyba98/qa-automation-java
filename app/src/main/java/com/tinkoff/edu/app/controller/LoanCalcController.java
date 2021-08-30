package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.service.LoanCalcService;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    private LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    /**
     * Validates and logs request
     */
    public LoanResponse createRequest(LoanRequest request, int requestId) {
        log();

        return  loanCalcService.createRequest(request, requestId);
    }
}
