package com.tinkoff.edu.app;

public class LoanCalcController {
    /**
     * Validates and logs request
     */
    public static int createRequest() {
        //param validation
        //log request
        LoanCalcLogger.log();
        return LoanCalcService.createRequest();
    }
}
