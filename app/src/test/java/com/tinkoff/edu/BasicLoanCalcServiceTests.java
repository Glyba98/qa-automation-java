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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicLoanCalcServiceTests {
    private LoanRequest request;
    private static LoanCalcController sut;

    @BeforeAll
    public static void createSut() {
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository()));
    }

    @Test
    public void shouldGetApproveWhenBoundaryValuesRequestForPerson() {
        int approvingMonths = 12;
        request = new LoanRequest("Yuriy Dudi", approvingMonths, BigDecimal.valueOf(10000), ClientType.PERSON);

         LoanResponse actualResponse = sut.createResponse(request)._2;
        LoanResponse expectedResponse = new LoanResponse(request, ResponseType.APPROVED);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldNotApproveWhenMonthsLowerThenMaxForPerson() {
        request = new LoanRequest("Ivan Urgant",13, BigDecimal.valueOf(10000), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApproveWhenAmountLowerThenMaxForPerson() {
        request = new LoanRequest("Oleg Tinkov", 10, BigDecimal.valueOf(10001), ClientType.PERSON);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }


    @Test
    public void shouldGetErrorWhenApplyNullRequest() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> {
            sut.createResponse(null);
        });
        assertEquals( "Передана заявка без данных", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenClientTypeNotSupported() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest("Roga i Copyta", 1, BigDecimal.valueOf(1000), ClientType.OAO);
            sut.createResponse(request);
        });
        assertEquals( "Неизвестный тип клиента", e.getMessage());
    }

    @Test
    public void shouldNotApprovedWhenClientTypeIsIP() {
        request = new LoanRequest("Anastasiya", 1, BigDecimal.valueOf(1000), ClientType.IP);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenAmountLess10000ForOOO() {
        request = new LoanRequest("TCS Groups", 1, BigDecimal.valueOf(9999), ClientType.OOO);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenAmountIs10000ForOOO() {
        request = new LoanRequest("Twenty Century Fox", 1, BigDecimal.valueOf(10000), ClientType.OOO);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenMonthsMore12ForOOO() {
        request = new LoanRequest("OOO Gazprom ", 13, BigDecimal.valueOf(10001), ClientType.OOO);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenMonthsIs12ForOOO() {
        request = new LoanRequest("Fantasy you call me", 12, BigDecimal.valueOf(10001), ClientType.OOO);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldApprovedWhenMonthsLess12AndAmountMore10000ForOOO() {
        request = new LoanRequest("Prazdnik Prazdnik Prazdnik", 11, BigDecimal.valueOf(10001), ClientType.OOO);
        LoanResponse response = sut.createResponse(request)._2;

        assertEquals(ResponseType.APPROVED, response.getResponseType(), "Займ должен был быть одобрен");
    }
}
