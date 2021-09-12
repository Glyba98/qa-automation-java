package com.tinkoff.edu.app.service;


import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.exceptions.IncorrectSizeOfFullnameStringExceptions;
import com.tinkoff.edu.app.exceptions.InvalidAmountException;
import com.tinkoff.edu.app.exceptions.InvalidCharacterInFullnameException;
import com.tinkoff.edu.app.exceptions.InvalidNumberOfMonthsException;
import com.tinkoff.edu.app.exceptions.StorageIsFullException;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

import java.math.BigDecimal;
import java.util.UUID;

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
    public LoanResponse createResponse(LoanRequest request) {
        if (request == null)
            throw new NullPointerException("Передана заявка без данных");

        BigDecimal maxAmount = new BigDecimal(10000);
        BigDecimal requestAmount = request.getAmount();
        int requestMonths = request.getMonths();

        if (request.getFullname().length() < 10 || request.getFullname().length() > 100)
            throw new IncorrectSizeOfFullnameStringExceptions("ФИО должны быть не короче 10 и не длиннее 100 символов");

        if (!request.getFullname().matches("[a-zA-Z[ ]]"))
            throw new InvalidCharacterInFullnameException("ФИО должны содержать только латинские буквы алфавита и пробел");

        if (requestAmount.doubleValue() <= 0.01 || requestAmount.doubleValue() > 999_999.99)
            throw new InvalidAmountException("Сумма кредита должна быть не меньше 0.01 и не больше 999999.99");

        if (requestMonths < 1 || requestMonths > 100)
            throw new InvalidNumberOfMonthsException("Срок кредита должен быть от 1 до 100 месяцев");

        ResponseType responseType = getResponseType(request.getType(), maxAmount, requestAmount, requestMonths);

        try {
            return loanCalcRepository.save(request, responseType);
        } catch (StorageIsFullException e) {
            return new LoanResponse(UUID.fromString("00000000-0000-0000-0000-000000000000"), request, ResponseType.ERROR);
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
