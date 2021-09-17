package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.exceptions.RecordNotFoundException;
import io.vavr.Tuple2;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class VariableLoanCalcRepository implements LoanCalcRepository {
    private java.util.HashMap<UUID, LoanResponse> loanResponses;
    private int lastRecord = 0;

    public VariableLoanCalcRepository() {
        loanResponses = new HashMap<UUID, LoanResponse>();
    }

    /**
     * Сохранение ID заявки
     *
     * @param request параметры заявки
     * @return Request Id
     */
    public Tuple2<UUID, LoanResponse> save(LoanRequest request, ResponseType responseType) {
        UUID uuid = UUID.randomUUID();
        LoanResponse response = new LoanResponse(request, responseType);
        loanResponses.put(uuid, response);
        Tuple2<UUID, LoanResponse> savedItem = new Tuple2<>(uuid,response);
        return savedItem;
    }

    /**
     * Получение данных по ID заявки
     *
     * @param uuid id заявки
     * @return Response
     */
    public LoanResponse getResponseByUUID(@NotNull UUID uuid) throws RecordNotFoundException {
        if (uuid == null)
            throw new NullPointerException("Передан пустой uuid");

        LoanResponse response = (LoanResponse) loanResponses.get(uuid);

        if (response == null)
            throw new RecordNotFoundException("Заявка с таким UUID не найдена");

        return response;
    }

    /**
     * Изменение типа ответа по ID заявки
     *
     * @param uuid         id заявки
     * @param responseType Устанавливаемый тип ответа на заявку
     */
    public void setResponseTypeByUUID(UUID uuid, ResponseType responseType) throws RecordNotFoundException {
        if (uuid == null)
            throw new NullPointerException("Передан пустой uuid");

        LoanResponse response = loanResponses.get(uuid);

        if (response == null)
            throw new RecordNotFoundException("Заявка с таким UUID не найдена");

        response.setResponseType(responseType);
        loanResponses.put(uuid, response);
    }

}
