package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.LoanDecision;

public class StaticVariableLoanCalcRepository implements LoanCalcRepository {
    private static int requestId;

    /**
     *  TODO persists request
     * @return Request Id
     */
    public LoanResponse save(LoanRequest request, int requestId) {
        //....
        LoanResponse response = new LoanResponse(++requestId, request, LoanDecision.APPROVED);
        return  response;
    }
}
