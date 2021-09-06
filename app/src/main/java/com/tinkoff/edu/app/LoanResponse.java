package com.tinkoff.edu.app;

import com.tinkoff.edu.app.dictionary.ResponseType;

import java.util.Objects;
import java.util.UUID;

public class LoanResponse {
    private final UUID uuid;
    private final LoanRequest request;
    private ResponseType responseType;

    public LoanResponse(UUID uuid, LoanRequest request, ResponseType responseType) {
        this.uuid = uuid;
        this.request = request;
        this.responseType = responseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponse response = (LoanResponse) o;
        return uuid == response.uuid && Objects.equals(request, response.request) && responseType == response.responseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, request, responseType);
    }

    public UUID getUuid() {
        return uuid;
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
                "requestId=" + uuid +
                ", request=" + request +
                ", responseType=" + responseType +
                '}';
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}
