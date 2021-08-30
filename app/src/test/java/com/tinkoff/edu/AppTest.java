package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.LoanType;
import com.tinkoff.edu.app.repository.StaticVariableLoanCalcRepository;
import com.tinkoff.edu.app.service.IpNotFriendlyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Loan Calc Tests.
 */
public class AppTest {
    private  LoanRequest request;
    private LoanCalcController sut;

    public AppTest() {
        System.out.println("Object created\n");
    }

    @BeforeEach
    public void init() {
        request = new LoanRequest(10, 1000, LoanType.PERSON);
        sut = new LoanCalcController(new IpNotFriendlyService(new StaticVariableLoanCalcRepository()));
    }

    @Test
    public void shouldGet1WhenFirstRequest() {
        int requestId = sut.createRequest(request, 0).getRequestId();
        assertEquals(1, requestId);
    }

    @Test
    public void shouldGetIncrementedIdWhenAnyCall() {
        int requestId = new Random().nextInt(100);
        int expectedRequestId = requestId + 1;
        int actualRequestId = sut.createRequest(request, requestId).getRequestId();

        assertEquals(expectedRequestId, actualRequestId, "Значение requestId не совало с о ожидаемым");
    }

}
