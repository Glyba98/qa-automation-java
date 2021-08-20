package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    /**
     * Validates and logs request
     */
    public static int createRequest(LoanRequest request) {
        log();
        return LoanCalcService.createRequest(request);
    }
}
