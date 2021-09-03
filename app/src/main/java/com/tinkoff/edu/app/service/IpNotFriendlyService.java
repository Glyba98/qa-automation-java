package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

public class IpNotFriendlyService extends BasicLoanCalcService {
    public IpNotFriendlyService(LoanCalcRepository loanCalcRepository) {
        super(loanCalcRepository);
    }

    @Override
    public LoanResponse createRequest(LoanRequest request) {
        if (request.getType().equals(ClientType.IP)) {
            return new LoanResponse(-1, null, ResponseType.ERROR);
        }
        return super.createRequest(request);

    }
}
