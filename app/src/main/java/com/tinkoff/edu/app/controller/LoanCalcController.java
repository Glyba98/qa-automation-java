package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.service.LoanCalcService;

public class LoanCalcController {
    private LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    /**
     * Validates and logs request
     */
    public LoanResponse createRequest(LoanRequest request) {
        if (request == null) throw new IllegalArgumentException();

        if (request.getMonths() > 12) {
            return new LoanResponse(-1, request, ResponseType.NOT_APPROVED);
        } else {
            return new LoanResponse(loanCalcService.createRequest(request).getRequestId(), request, ResponseType.APPROVED);
        }
    }
}
