package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;

import static com.tinkoff.edu.app.LoanCalcController.createRequest;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest loanRequest;
        int requestId = createRequest(loanRequest);

        System.out.println(requestId + " must be 1");
    }
}
