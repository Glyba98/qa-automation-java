package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ResponseType;

public class VariableLoanCalcRepository implements LoanCalcRepository {
    private int requestId;

    public VariableLoanCalcRepository(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    /**
     * TODO persists request
     *
     * @return Request Id
     */
    public LoanResponse save(LoanRequest request) {
        //....
        LoanResponse response = new LoanResponse(++requestId, request, ResponseType.APPROVED);
        return response;
    }
}
