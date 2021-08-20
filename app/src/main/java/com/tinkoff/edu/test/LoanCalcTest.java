package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;


/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request = new LoanRequest(10, 1000, LoanType.IP);
        LoanCalcController loanCalcController = new LoanCalcController();

        int requestId = loanCalcController.createRequest(request).getRequestId();

        System.out.println("Request: " + request);
        System.out.println(requestId + " must be 1");
    }
}
