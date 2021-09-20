package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ResponseType;
import io.vavr.Tuple2;

import java.util.UUID;

public interface LoanCalcRepository {
    Tuple2<UUID, LoanResponse> save(LoanRequest request, ResponseType responseType);
}
