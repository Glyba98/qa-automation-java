package com.tinkoff.edu.app;

public class LoanCalcRepository {
    private static int requestId;

    /**
     *  TODO persists request
     * @return Request Id
     */
    public LoanResponse save(LoanRequest request) {
        //....
        LoanResponse response = new LoanResponse(++requestId, request, LoanDecision.APPROVED);
        return  response;
    }
}
