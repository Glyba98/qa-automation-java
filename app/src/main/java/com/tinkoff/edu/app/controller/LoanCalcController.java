package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.service.LoanCalcService;

public class LoanCalcController {
    private LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    /**
     * Validates and logs request
     */
    public LoanResponse createResponse(LoanRequest request) {
        return loanCalcService.createResponse(request);
    }
}
