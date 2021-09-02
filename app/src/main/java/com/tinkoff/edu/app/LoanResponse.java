package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.ResponseType;

public class LoanResponse {
    private final int requestId;
    private final LoanRequest request;
    private final ResponseType responseType;

    public LoanResponse(int requestId, LoanRequest request, ResponseType responseType) {
        this.requestId = requestId;
        this.request = request;
        this.responseType = responseType;
    }

    public int getRequestId() {
        return requestId;
    }

    public LoanRequest getRequest() {
        return request;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String toString() {
        return "LoanResponse{" +
                "requestId=" + requestId +
                '}';
    }
}
