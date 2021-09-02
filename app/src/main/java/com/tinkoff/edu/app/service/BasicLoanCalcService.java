package com.tinkoff.edu.app.service;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.LoanType;
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

        if (requestAmount.compareTo(new BigDecimal(0)) <= 0)
            throw new IllegalArgumentException("Сумма кредита должна быть больше 0");

        if (requestMonths <= 0)
            throw new IllegalArgumentException("Срок кредита должен быть больше 0");

        ResponseType responseType = getResponseType(request.getType(), maxAmount, requestAmount, requestMonths);

        return loanCalcRepository.save(request, responseType);
    }

    private ResponseType getResponseType(LoanType clientType, BigDecimal cornerAmount, BigDecimal amount, int months) {
        switch (clientType) {
            case PERSON:
                return getRespStatusForPerson(cornerAmount, amount, months);
            case OOO:
                return getRespStatusForOoo(cornerAmount, amount, months);
            case IP:
                return getRespStatusForIp();
            default:
                throw new NullPointerException("Неизвестный тип клиента");
        }
    }

    private ResponseType getRespStatusForPerson(BigDecimal cornerAmount, BigDecimal amount, int months) {

        if (amount.compareTo(cornerAmount) <= 0 && months <= 12) {
            return ResponseType.APPROVED;
        } else {
            return ResponseType.NOT_APPROVED;
        }
    }

    private ResponseType getRespStatusForOoo(BigDecimal cornerAmount, BigDecimal amount, int months) {

        if (amount.compareTo(cornerAmount) > 0 && months <= 12) {
            return ResponseType.APPROVED;
        } else {
            return ResponseType.NOT_APPROVED;
        }
    }


    private ResponseType getRespStatusForIp() {
        return ResponseType.NOT_APPROVED;
    }
}
