package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.ResponseType;

import java.util.Objects;

public class LoanResponse {
    private final int requestId;
    private final LoanRequest request;
    private final ResponseType responseType;

    public LoanResponse(int requestId, LoanRequest request, ResponseType responseType) {
        this.requestId = requestId;
        this.request = request;
        this.responseType = responseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponse response = (LoanResponse) o;
        return requestId == response.requestId && Objects.equals(request, response.request) && responseType == response.responseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, request, responseType);
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

    @Override
    public String toString() {
        return "LoanResponse{" +
                "requestId=" + requestId +
                ", request=" + request +
                ", responseType=" + responseType +
                '}';
    }
}
