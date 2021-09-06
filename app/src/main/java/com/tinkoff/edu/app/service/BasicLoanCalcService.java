package com.tinkoff.edu.app.service;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.StorageIsFullException;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

import java.math.BigDecimal;

public class BasicLoanCalcService implements LoanCalcService {
    private final LoanCalcRepository loanCalcRepository;

    /**
     * Constructor DI
     * @param loanCalcRepository
     */
    public BasicLoanCalcService(LoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    /**
     * Loan calculation
     */
    public LoanResponse createRequest(LoanRequest request) {
        if (request == null)
            throw new NullPointerException("Передана заявка без данных");

        BigDecimal maxAmount = new BigDecimal(10000);
        BigDecimal requestAmount = request.getAmount();
        int requestMonths = request.getMonths();

        if (requestAmount.signum() <= 0)
            throw new IllegalArgumentException("Сумма кредита должна быть больше 0");

        if (requestMonths <= 0)
            throw new IllegalArgumentException("Срок кредита должен быть больше 0");

        ResponseType responseType = getResponseType(request.getType(), maxAmount, requestAmount, requestMonths);

        try {
            return loanCalcRepository.save(request, responseType);
        } catch (StorageIsFullException e) {
            return null;
        }
    }

    private ResponseType getResponseType(ClientType clientType, BigDecimal cornerAmount, BigDecimal amount, int months) {
        switch (clientType) {
            case PERSON:
                return getResponseStatusForPerson(cornerAmount, amount, months);
            case OOO:
                return getResponseStatusForOoo(cornerAmount, amount, months);
            case IP:
                return getResponseStatusForIp();
            default:
                throw new IllegalArgumentException("Неизвестный тип клиента");
        }
    }

    private ResponseType getResponseStatusForPerson(BigDecimal cornerAmount, BigDecimal amount, int months) {

        if (amount.compareTo(cornerAmount) <= 0 && months <= 12) {
            return ResponseType.APPROVED;
        } else {
            return ResponseType.NOT_APPROVED;
        }
    }

    private ResponseType getResponseStatusForOoo(BigDecimal cornerAmount, BigDecimal amount, int months) {

        if (amount.compareTo(cornerAmount) > 0 && months < 12) {
            return ResponseType.APPROVED;
        } else {
            return ResponseType.NOT_APPROVED;
        }
    }


    private ResponseType getResponseStatusForIp() {
        return ResponseType.NOT_APPROVED;
    }


}
