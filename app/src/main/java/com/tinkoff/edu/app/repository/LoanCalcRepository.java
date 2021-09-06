package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.StorageIsFullException;
import com.tinkoff.edu.app.dictionary.ResponseType;

public interface LoanCalcRepository {
    LoanResponse save(LoanRequest request, ResponseType responseType) throws StorageIsFullException;
}
