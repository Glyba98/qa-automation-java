package com.tinkoff.edu.app;

public class LoanResponse {
    private final int requestId;

    public LoanResponse(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    public String toString() {
        return "LoanResponse{" +
                "requestId=" + requestId +
                '}';
    }
}
