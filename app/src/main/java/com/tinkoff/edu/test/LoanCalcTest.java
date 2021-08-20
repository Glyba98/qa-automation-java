package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;

import static com.tinkoff.edu.app.LoanCalcController.createRequest;

/**
 * Loan Calc Tests
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request = new LoanRequest(10, 1000, LoanType.IP);
        int requestId = createRequest(request);
        System.out.println(request);

        System.out.println(requestId + " must be 1");
    }
}
