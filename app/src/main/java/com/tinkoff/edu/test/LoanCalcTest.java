package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.LoanType;
import com.tinkoff.edu.app.repository.StaticVariableLoanCalcRepository;
import com.tinkoff.edu.app.service.IpNotFriendlyService;
import com.tinkoff.edu.app.service.LoanCalcService;


/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request = new LoanRequest(10, 1000, LoanType.IP);
        LoanCalcService loanCalcService = new IpNotFriendlyService(new StaticVariableLoanCalcRepository());
        LoanCalcController loanCalcController = new LoanCalcController(loanCalcService);

        int requestId = loanCalcController.createRequest(request).getRequestId();

        System.out.println("Request: " + request);
        System.out.println(requestId + " must be 1");
    }
}
