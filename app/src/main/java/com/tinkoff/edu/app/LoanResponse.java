package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.LoanDecision;

public class LoanResponse {
    private final int requestId;
    private final LoanRequest request;
    private final LoanDecision decision;

    public LoanResponse(int requestId, LoanRequest request, LoanDecision decision) {
        this.requestId = requestId;
        this.request = request;
        this.decision = decision;
    }

    public int getRequestId() {
        return requestId;
    }

    public LoanRequest getRequest() {
        return request;
    }

    public LoanDecision getDecision() {
        return decision;
    }

    public String toString() {
        return "LoanResponse{" +
                "requestId=" + requestId +
                '}';
    }
}
