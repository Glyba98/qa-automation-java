package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.exceptions.RecordNotFoundException;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.BasicLoanCalcService;
import io.vavr.Tuple2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RepositoryTests {
    private LoanRequest request;
    private static VariableLoanCalcRepository repo;
    private static LoanCalcController sut;

    @BeforeAll
    public static void createSut() {
        repo = new VariableLoanCalcRepository();
        sut = new LoanCalcController(new BasicLoanCalcService(repo));
    }

    @Test
    public void shouldGetExceptionWhereTryGetNonExistentRecord() {
        request = new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.OOO);
        RecordNotFoundException e = assertThrows(RecordNotFoundException.class, () -> {
            for (int i = 0; i < 10; i++)
                repo.save(request, ResponseType.APPROVED);
            repo.getResponseByUUID(UUID.randomUUID());
        });

        assertEquals("Заявка с таким UUID не найдена", e.getMessage());
    }

    @Test
    public void shouldGetExceptionWhereTrySetStatusOfNonExistentRecord() {
        request = new LoanRequest("FizzBuzz", 11, BigDecimal.valueOf(10001), ClientType.OOO);
        VariableLoanCalcRepository repository = new VariableLoanCalcRepository();
        RecordNotFoundException e = assertThrows(RecordNotFoundException.class, () -> {
            for (int i = 0; i < 10; i++)
                repository.save(request, ResponseType.APPROVED);
            repository.setResponseTypeByUUID(UUID.randomUUID(), ResponseType.NOT_APPROVED);
        });

        assertEquals("Заявка с таким UUID не найдена", e.getMessage());
    }

    @Test
    public void shouldGetResponseByUUID() {
        request = new LoanRequest("FizzBuzz Enterprise edition", 11, BigDecimal.valueOf(10001), ClientType.OOO);
        Tuple2<UUID, LoanResponse> expectedResponse = sut.createResponse(request);
        try {
            LoanResponse actualResponse = repo.getResponseByUUID(expectedResponse._1);
            assertEquals(expectedResponse._2, actualResponse, "Ответ сервиса не совпадает с записью в базе");
        } catch (RecordNotFoundException e) {
            assertFalse(true, e.getMessage());
        }
    }

    @Test
    public void shouldSetResponseTypeByUUID() {
        request = new LoanRequest("Ololo Ololoevich", 10, BigDecimal.valueOf(1000), ClientType.PERSON);
        Tuple2<UUID, LoanResponse> response = sut.createResponse(request);
        try {
            repo.setResponseTypeByUUID(response._1, ResponseType.NOT_APPROVED);
            assertEquals(ResponseType.NOT_APPROVED, response._2.getResponseType(), "ResponseType не установился или установился некорректно");
        } catch (RecordNotFoundException e) {
            assertFalse(true, e.getMessage());
        }
    }
}
