package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.BasicLoanCalcService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetInfoByClientTypeTests {
    private LoanRequest request;
    private static VariableLoanCalcRepository repo;
    private static LoanCalcController sut;

    @BeforeAll
    public static void createSut() {
        repo = new VariableLoanCalcRepository();
        sut = new LoanCalcController(new BasicLoanCalcService(repo));
    }

    @Test
    public void shouldGetZeroSumWhereHaveNotOOORecord() {
        BigDecimal sum  = repo.getSumRequestsOfOOO();

        assertEquals(sum, BigDecimal.valueOf(0), "Сервис вернул сумму, отличную от 0");
    }

    @Test
    public void shouldGetCorrectSumWhereHaveSeveralOOORecords() {
        new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.OOO);
        new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.OOO);
        BigDecimal sum  = repo.getSumRequestsOfOOO();

        assertEquals(BigDecimal.valueOf(20002), sum, "Сервис вернул неверную сумму");
    }
}
