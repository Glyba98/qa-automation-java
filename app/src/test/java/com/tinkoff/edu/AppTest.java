package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.BasicLoanCalcService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Loan Calc Tests.
 */
public class AppTest {
    private LoanRequest request;
    private LoanCalcController sut;

    public AppTest() {
        System.out.println("Object created\n");
    }

    @Test
    public void shouldGetApproveWhenValidRequest() {
        request = new LoanRequest(10, BigDecimal.valueOf(1000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));

        LoanResponse actualResponse = sut.createRequest(request);
        LoanResponse expectedResponse = new LoanResponse(actualResponse.getUuid(), request,ResponseType.APPROVED);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldGetApproveWhenBoundaryValuesRequestForPerson() {
        int requestId = 1;
        int approvingMonths = 12;
        request = new LoanRequest(approvingMonths, BigDecimal.valueOf(10000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(requestId)));

        LoanResponse actualResponse = sut.createRequest(request);
        LoanResponse expectedResponse = new LoanResponse(actualResponse.getUuid(), request,ResponseType.APPROVED);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldGetRequestDataInResponse() {
        request = new LoanRequest(11, BigDecimal.valueOf(10000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(request, response.getRequest(), "В ответе вернулись неверные данные заявки: " + response.toString());
    }

    @Test
    public void shouldNotApproveWhenMonthsLowerThenMaxForPerson() {
        request = new LoanRequest(13, BigDecimal.valueOf(10000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApproveWhenAmountLowerThenMaxForPerson() {
        request = new LoanRequest(10, BigDecimal.valueOf(10001), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }


    @Test
    public void shouldGetErrorWhenApplyNullRequest() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> {
            sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
            sut.createRequest(null);
        });
        assertEquals( "Передана заявка без данных", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest(1, BigDecimal.valueOf(-1000), ClientType.PERSON);
            sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
            sut.createRequest(request);
        });
        assertEquals( "Сумма кредита должна быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenApplyZeroAmountRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest(1, BigDecimal.valueOf(0), ClientType.PERSON);
            sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
            sut.createRequest(request);
        });
        assertEquals( "Сумма кредита должна быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenMonthsNegativeRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest(-1, BigDecimal.valueOf(1000), ClientType.PERSON);
            sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
            sut.createRequest(request);
        });
        assertEquals( "Срок кредита должен быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenMonthsZeroRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest(0, BigDecimal.valueOf(1000), ClientType.PERSON);
            sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
            sut.createRequest(request);
        });
        assertEquals( "Срок кредита должен быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenClientTypeNotSupported() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest(1, BigDecimal.valueOf(1000), ClientType.OAO);
            sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
            sut.createRequest(request);
        });
        assertEquals( "Неизвестный тип клиента", e.getMessage());
    }

    @Test
    public void shouldNotApprovedWhenClientTypeIsIP() {
        request = new LoanRequest(1, BigDecimal.valueOf(1000), ClientType.IP);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenAmountLess10000ForOOO() {
        request = new LoanRequest(1, BigDecimal.valueOf(9999), ClientType.OOO);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenAmountIs10000ForOOO() {
        request = new LoanRequest(1, BigDecimal.valueOf(10000), ClientType.OOO);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenMonthsMore12ForOOO() {
        request = new LoanRequest(13, BigDecimal.valueOf(10001), ClientType.OOO);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenMonthsIs12ForOOO() {
        request = new LoanRequest(12, BigDecimal.valueOf(10001), ClientType.OOO);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldApprovedWhenMonthsLess12AndAmountMore10000ForOOO() {
        request = new LoanRequest(11, BigDecimal.valueOf(10001), ClientType.OOO);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.APPROVED, response.getResponseType(), "Займ должен был быть одобрен");
    }

    @Test
    public void shouldGetTrueWhenCompareSameResponses() {
        request = new LoanRequest(11, BigDecimal.valueOf(1000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertEquals(response, response, "Объекты должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithOtherObject() {
        request = new LoanRequest(11, BigDecimal.valueOf(1000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertNotEquals(response, request, "Объекты НЕ должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithNull() {
        request = new LoanRequest(11, BigDecimal.valueOf(1000), ClientType.PERSON);
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository(0)));
        LoanResponse response = sut.createRequest(request);

        assertNotEquals(response, null, "Объекты НЕ должны быть эквивалентны");
    }
}
;