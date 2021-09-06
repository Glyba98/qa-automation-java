package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.StorageIsFullException;
import com.tinkoff.edu.app.dictionary.ResponseType;

import java.util.Objects;
import java.util.UUID;

public class VariableLoanCalcRepository implements LoanCalcRepository {
    private LoanResponse[] loanResponses;
    private int lastRecord = 0;

    public VariableLoanCalcRepository() {
        loanResponses = new LoanResponse[100_000];
    }

    /**
     * Сохранение ID заявки
     *
     * @param request параметры заявки
     * @return Request Id
     */
    public LoanResponse save(LoanRequest request, ResponseType responseType) throws StorageIsFullException {
        //....
        if (lastRecord < 100000) {
            UUID uuid = UUID.randomUUID();
            LoanResponse response = new LoanResponse(uuid, request, responseType);
            loanResponses[++lastRecord] = response;
            return response;
        } else {
            throw new StorageIsFullException("Хранилище заполнено!!");
        }
    }

    /**
     * Получение данных по ID заявки
     *
     * @param uuid id заявки
     * @return Response
     */
    public LoanResponse getResponseByUUID(UUID uuid) {
        for (int i = 0; i < loanResponses.length; i++) {
            if (Objects.equals(loanResponses[i].getUuid(), uuid)) {
                return loanResponses[i];
            }
        }
        return null;
    }

    /**
     * Изменение типа ответа по ID заявки
     *
     * @param uuid id заявки
     * @return Response
     */
    public void setResponseTypeByUUID(UUID uuid, ResponseType responseType) {
        for (int i = 0; i < loanResponses.length; i++) {
            if (Objects.equals(loanResponses[i].getUuid(), uuid)) {
                loanResponses[i].setResponseType(responseType);
                break;
            }
        }
    }
}
