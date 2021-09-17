package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import io.vavr.Tuple2;

import java.util.UUID;

public interface LoanCalcService {
    Tuple2<UUID, LoanResponse> createResponse(LoanRequest request);
}
