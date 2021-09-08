package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.dictionary.ResponseType;
import com.tinkoff.edu.app.exceptions.StorageIsFullException;

public interface LoanCalcRepository {
    LoanResponse save(LoanRequest request, ResponseType responseType) throws StorageIsFullException;
}
