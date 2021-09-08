package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.BasicLoanCalcService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Loan Calc Tests.
 */
public class AppTest {
    private LoanRequest request;
    private static LoanCalcController sut;

    public AppTest() {
        System.out.println("Object created\n");
    }

    @BeforeAll
    public static void createSut() {
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository()));
    }

    @Test
    public void shouldGetApproveWhenValidRequest() {
        request = new LoanRequest("Ololo Ololoevich", 10, BigDecimal.valueOf(1000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository()));

        LoanResponse actualResponse = sut.createResponse(request);
        LoanResponse expectedResponse = new LoanResponse(actualResponse.getUuid(), request,ResponseType.APPROVED);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldGetRequestDataInResponse() {
        request = new LoanRequest("Khalisi Stormborn",11, BigDecimal.valueOf(10000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request);

        assertEquals(request, response.getRequest(), "В ответе вернулись неверные данные заявки: " + response.toString());
    }



    @Test
    public void shouldGetTrueWhenCompareSameResponses() {
        request = new LoanRequest("Stoya", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request);

        assertEquals(response, response, "Объекты должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithOtherObject() {
        request = new LoanRequest("John Snow", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request);

        assertNotEquals(response, request, "Объекты НЕ должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithNull() {
        request = new LoanRequest("Iron Man", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request);

        assertNotEquals(response, null, "Объекты НЕ должны быть эквивалентны");
    }

}
