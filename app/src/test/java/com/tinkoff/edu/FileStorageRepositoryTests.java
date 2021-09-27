package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.dictionary.ClientType;
import com.tinkoff.edu.app.repository.FileStorageLoanCalcRepository;
import com.tinkoff.edu.app.service.BasicLoanCalcService;
import io.vavr.Tuple2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileStorageRepositoryTests {

    private String fileName = "./src/main/java/com/tinkoff/edu/app/repository/LoanResponses.txt";
    private LoanRequest request;
    private static FileStorageLoanCalcRepository repo;
    private static LoanCalcController sut;

    @BeforeAll
    public static void createSut() {
        repo = new FileStorageLoanCalcRepository();
        sut = new LoanCalcController(new BasicLoanCalcService(repo));
    }

    @Test
    public void shouldSaveResponsesToTextFile() {
        request = new LoanRequest("Text in text file", 11, BigDecimal.valueOf(1000), ClientType.PERSON);
        Tuple2<UUID, LoanResponse> response = sut.createResponse(request);

        try {
            Path path = Paths.get(fileName);
            List<String> allLines = Files.readAllLines(path);
            String savedResponse = allLines.get(allLines.size() - 1);
            assertEquals(response._1 + " " + response._2.toString(), savedResponse, "Объекты должны быть эквивалентны");
        } catch ( IOException e) {
            assertTrue(false, "Не удалось считать файл");
        }

    }
}
