package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.ResponseType;

import java.util.Objects;

public class LoanResponse {
    private final LoanRequest request;
    private ResponseType responseType;

    public LoanResponse( LoanRequest request, ResponseType responseType) {
        this.request = request;
        this.responseType = responseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponse response = (LoanResponse) o;
        return Objects.equals(request, response.request) && responseType == response.responseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash( request, responseType);
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
                "request=" + request +
                ", responseType=" + responseType +
                '}';
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}
