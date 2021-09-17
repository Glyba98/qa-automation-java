package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.BasicLoanCalcService;
import io.vavr.Tuple2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

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
        Tuple2<UUID, LoanResponse> response= sut.createResponse(request);
        LoanResponse expectedResponse = new LoanResponse(request,ResponseType.APPROVED);

        assertEquals(expectedResponse, response._2);
    }

    @Test
    public void shouldGetRequestDataInResponse() {
        request = new LoanRequest("Khalisi Stormborn",11, BigDecimal.valueOf(10000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(request, response.getRequest(), "В ответе вернулись неверные данные заявки: " + response.toString());
    }



    @Test
    public void shouldGetTrueWhenCompareSameResponses() {
        request = new LoanRequest("Stoyadinovich", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(response, response, "Объекты должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithOtherObject() {
        request = new LoanRequest("John Snow you dont know", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request)._2;

        assertNotEquals(response, request, "Объекты НЕ должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithNull() {
        request = new LoanRequest("Iron Man Stark", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request)._2;

        assertNotEquals(response, null, "Объекты НЕ должны быть эквивалентны");
    }

}
