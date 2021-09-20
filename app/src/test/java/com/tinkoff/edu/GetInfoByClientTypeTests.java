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
import java.util.HashMap;
import java.util.UUID;

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
        repo.save(new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.OOO), ResponseType.APPROVED);
        repo.save(new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.OOO), ResponseType.APPROVED);
        BigDecimal sum  = repo.getSumRequestsOfOOO();

        assertEquals(BigDecimal.valueOf(20002), sum, "Сервис вернул неверную сумму");
    }

    @Test
    public void shouldGetCorrectMapWhereHaveSeveralIpRecords() {
        Tuple2<UUID, LoanResponse> pair1 = repo
                .save(new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.IP), ResponseType.APPROVED);
        Tuple2<UUID, LoanResponse> pair2 = repo
                .save(new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.IP), ResponseType.APPROVED);
        repo.save(new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.OAO), ResponseType.APPROVED);

        HashMap<UUID, LoanResponse> oooResponses  = repo.getResponsesByClientType(ClientType.IP);

        HashMap<UUID, LoanResponse> expectedMap = new HashMap<>();
        expectedMap.put(pair1._1, pair1._2);
        expectedMap.put(pair2._1, pair2._2);

        assertEquals(expectedMap, oooResponses, "Сервис вернул неверный список заявок");
    }
}
