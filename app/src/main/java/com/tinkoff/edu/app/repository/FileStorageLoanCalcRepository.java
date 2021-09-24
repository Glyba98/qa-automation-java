package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ResponseType;
import io.vavr.Tuple2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileStorageLoanCalcRepository implements LoanCalcRepository {
    private final String loanResponseFile = "LoanResponses.txt";
    private String filePath = "./src/main/java/com/tinkoff/edu/app/repository/";
    private java.util.HashMap<UUID, LoanResponse> loanResponses;

    public FileStorageLoanCalcRepository() {
        loanResponses = new HashMap<UUID, LoanResponse>();
    }

    /**
     * Сохранение ID заявки
     *
     * @param request параметры заявки
     * @return Request Id
     */
    public Tuple2<UUID, LoanResponse> save(LoanRequest request, ResponseType responseType) {
        UUID uuid = UUID.randomUUID();
        LoanResponse response = new LoanResponse(request, responseType);

        writeLineToFile(loanResponseFile, uuid + " " + response.toString());
        return new Tuple2<>(uuid,response);
    }

    void writeLineToFile(String fileName, String textLine) {
        textLine += System.lineSeparator();

        try {
            Path path = Path.of(filePath + fileName);
            Files.writeString(path, textLine, CREATE, APPEND, WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
