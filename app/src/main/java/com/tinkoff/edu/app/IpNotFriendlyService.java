package com.tinkoff.edu.app;

public class IpNotFriendlyService extends BasicLoanCalcService {
    public IpNotFriendlyService(LoanCalcRepository loanCalcRepository) {
        super(loanCalcRepository);
    }

    @Override
    public LoanResponse createRequest(LoanRequest request) {
        if (request.getType().equals(LoanType.IP)) {
            return new LoanResponse(-1, null, null);
        }
        return super.createRequest(request);

    }
}
