package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.StorageIsFullException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        LoanResponse actualResponse = sut.createRequest(request);
        LoanResponse expectedResponse = new LoanResponse(actualResponse.getUuid(), request,ResponseType.APPROVED);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldGetApproveWhenBoundaryValuesRequestForPerson() {
        int approvingMonths = 12;
        request = new LoanRequest("Yuriy Dud'", approvingMonths, BigDecimal.valueOf(10000), ClientType.PERSON);

        LoanResponse actualResponse = sut.createRequest(request);
        LoanResponse expectedResponse = new LoanResponse(actualResponse.getUuid(), request,ResponseType.APPROVED);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void shouldGetRequestDataInResponse() {
        request = new LoanRequest("Khalisi Stormborn",11, BigDecimal.valueOf(10000), ClientType.PERSON);
        LoanResponse response = sut.createRequest(request);

        assertEquals(request, response.getRequest(), "В ответе вернулись неверные данные заявки: " + response.toString());
    }

    @Test
    public void shouldNotApproveWhenMonthsLowerThenMaxForPerson() {
        request = new LoanRequest("Ivan Urgant",13, BigDecimal.valueOf(10000), ClientType.PERSON);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApproveWhenAmountLowerThenMaxForPerson() {
        request = new LoanRequest("Oleg Tinkov", 10, BigDecimal.valueOf(10001), ClientType.PERSON);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }


    @Test
    public void shouldGetErrorWhenApplyNullRequest() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> {
            sut.createRequest(null);
        });
        assertEquals( "Передана заявка без данных", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest("Diego Maradona", 1, BigDecimal.valueOf(-1000), ClientType.PERSON);
            sut.createRequest(request);
        });
        assertEquals( "Сумма кредита должна быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenApplyZeroAmountRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest("Eva Elfie", 1, BigDecimal.valueOf(0), ClientType.PERSON);
            sut.createRequest(request);
        });
        assertEquals( "Сумма кредита должна быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenMonthsNegativeRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest("nobody", -1, BigDecimal.valueOf(1000), ClientType.PERSON);
            sut.createRequest(request);
        });
        assertEquals( "Срок кредита должен быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenMonthsZeroRequest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest("Arkady Parovozov", 0, BigDecimal.valueOf(1000), ClientType.PERSON);
            sut.createRequest(request);
        });
        assertEquals( "Срок кредита должен быть больше 0", e.getMessage());
    }

    @Test
    public void shouldGetErrorWhenClientTypeNotSupported() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            request = new LoanRequest("Roga i Copyta", 1, BigDecimal.valueOf(1000), ClientType.OAO);
            sut.createRequest(request);
        });
        assertEquals( "Неизвестный тип клиента", e.getMessage());
    }

    @Test
    public void shouldNotApprovedWhenClientTypeIsIP() {
        request = new LoanRequest("Anastasiya", 1, BigDecimal.valueOf(1000), ClientType.IP);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenAmountLess10000ForOOO() {
        request = new LoanRequest("TCS Group", 1, BigDecimal.valueOf(9999), ClientType.OOO);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenAmountIs10000ForOOO() {
        request = new LoanRequest("Twenty Сentury Fox", 1, BigDecimal.valueOf(10000), ClientType.OOO);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenMonthsMore12ForOOO() {
        request = new LoanRequest("Gazprom ", 13, BigDecimal.valueOf(10001), ClientType.OOO);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldNotApprovedWhenMonthsIs12ForOOO() {
        request = new LoanRequest("Fantasy", 12, BigDecimal.valueOf(10001), ClientType.OOO);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.NOT_APPROVED, response.getResponseType(), "Займ не должен был быть одобрен");
    }

    @Test
    public void shouldApprovedWhenMonthsLess12AndAmountMore10000ForOOO() {
        request = new LoanRequest("Prazdnik Prazdnik Prazdnik", 11, BigDecimal.valueOf(10001), ClientType.OOO);
        LoanResponse response = sut.createRequest(request);

        assertEquals(ResponseType.APPROVED, response.getResponseType(), "Займ должен был быть одобрен");
    }

    @Test
    public void shouldGetTrueWhenCompareSameResponses() {
        request = new LoanRequest("Stoya", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createRequest(request);

        assertEquals(response, response, "Объекты должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithOtherObject() {
        request = new LoanRequest("John Snow", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createRequest(request);

        assertNotEquals(response, request, "Объекты НЕ должны быть эквивалентны");
    }

    @Test
    public void shouldGetFalseWhenCompareResponseWithNull() {
        request = new LoanRequest("Iron Man", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        LoanResponse response = sut.createRequest(request);

        assertNotEquals(response, null, "Объекты НЕ должны быть эквивалентны");
    }

    @Test
    public void shouldGetExceptionWherStorageIsFull() {
        request = new LoanRequest("StackOverflow", 11, BigDecimal.valueOf(10001), ClientType.OOO);

        StorageIsFullException e = assertThrows(StorageIsFullException.class, () -> {
        for (int i = 0; i < 101; i++)
            sut.createRequest(request);
        });

        assertEquals("Хранилище заполнено!!", e.getMessage());
    }
}
