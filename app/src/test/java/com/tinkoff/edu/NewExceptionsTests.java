package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.exceptions.IncorrectSizeOfFullnameStringExceptions;
import com.tinkoff.edu.app.exceptions.InvalidAmountException;
import com.tinkoff.edu.app.exceptions.InvalidCharacterInFullnameException;
import com.tinkoff.edu.app.exceptions.InvalidNumberOfMonthsException;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.BasicLoanCalcService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewExceptionsTests {

    private LoanRequest request;
    private static LoanCalcController sut;

    @BeforeAll
    public static void createSut() {
        sut = new LoanCalcController(new BasicLoanCalcService(new VariableLoanCalcRepository()));
    }

    @Test
    public void shouldGetExceptionWhenApplyShortFullnameInRequest() {
        IncorrectSizeOfFullnameStringExceptions e = assertThrows(IncorrectSizeOfFullnameStringExceptions.class, () -> {
            request = new LoanRequest("Diego", 1, BigDecimal.valueOf(1000), ClientType.PERSON);
            sut.createResponse(request);
        });
        assertEquals( "ФИО должны быть не короче 10 и не длиннее 100 символов", e.getMessage());
    }

    @Test
    public void shouldGetExceptionWhenApplyLongFullnameInRequest() {
        IncorrectSizeOfFullnameStringExceptions e = assertThrows(IncorrectSizeOfFullnameStringExceptions.class, () -> {
            request = new LoanRequest(
                    "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    , 1, BigDecimal.valueOf(1000), ClientType.PERSON);
            sut.createResponse(request);
        });
        assertEquals( "ФИО должны быть не короче 10 и не длиннее 100 символов", e.getMessage());
    }

    @Test
    public void shouldGetExceptionWhenApplySymbolFullnameInRequest() {
        InvalidCharacterInFullnameException e = assertThrows(InvalidCharacterInFullnameException.class, () -> {
            request = new LoanRequest("Van-Helsing", 1, BigDecimal.valueOf(1000), ClientType.PERSON);
            sut.createResponse(request);
        });
        assertEquals( "ФИО должны содержать только латинские буквы алфавита и пробел", e.getMessage());
    }

    @Test
    public void shouldGetExceptionWhenApplyZeroAmountInRequest() {
        InvalidAmountException e = assertThrows(InvalidAmountException.class, () -> {
            request = new LoanRequest("Diego Maradonna", 1, BigDecimal.valueOf(0), ClientType.PERSON);
            sut.createResponse(request);
        });
        assertEquals( "Сумма кредита должна быть не меньше 0.01 и не больше 999999.99", e.getMessage());
    }

    @Test
    public void shouldGetExceptionWhenApplyLargeAmountInRequest() {
        InvalidAmountException e = assertThrows(InvalidAmountException.class, () -> {
            request = new LoanRequest("Diego Maradonna", 1, BigDecimal.valueOf(1000000), ClientType.PERSON);
            sut.createResponse(request);
        });
        assertEquals( "Сумма кредита должна быть не меньше 0.01 и не больше 999999.99", e.getMessage());
    }

    @Test
    public void shouldGetExceptionWhenApplyZeroMonthsInRequest() {
        InvalidNumberOfMonthsException e = assertThrows(InvalidNumberOfMonthsException.class, () -> {
            request = new LoanRequest("Diego Maradonna", 0, BigDecimal.valueOf(110), ClientType.PERSON);
            sut.createResponse(request);
        });
        assertEquals( "Срок кредита должен быть от 1 до 100 месяцев", e.getMessage());
    }

    @Test
    public void shouldGetExceptionWhenApplyLargeNumberOfMonthsInRequest() {
        InvalidNumberOfMonthsException e = assertThrows(InvalidNumberOfMonthsException.class, () -> {
            request = new LoanRequest("Diego Maradonna", 101, BigDecimal.valueOf(110), ClientType.PERSON);
            sut.createResponse(request);
        });
        assertEquals( "Срок кредита должен быть от 1 до 100 месяцев", e.getMessage());
    }
}
