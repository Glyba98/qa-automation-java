package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.service.LoanCalcService;
import io.vavr.Tuple2;

import java.util.UUID;

public class LoanCalcController {
    private LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    /**
     * Validates and logs request
     */
    public Tuple2<UUID, LoanResponse> createResponse(LoanRequest request) {
        return loanCalcService.createResponse(request);
    }
}
