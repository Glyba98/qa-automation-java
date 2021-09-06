package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ResponseType;

import java.util.UUID;

public class VariableLoanCalcRepository implements LoanCalcRepository {
    private int requestId;

    public VariableLoanCalcRepository(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    /**
     * Сохранение ID заявки
     *
     * @param request параметры заявки
     * @return Request Id
     */
    public LoanResponse save(LoanRequest request, ResponseType responseType) {
        //....
        UUID uuid = UUID.randomUUID();
        LoanResponse response = new LoanResponse(uuid, request, responseType);
        return response;
    }
}
