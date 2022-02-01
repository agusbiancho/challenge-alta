package com.challenge.alta.service;

import com.challenge.alta.model.LoanInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {

    public Page<LoanInfoResponse> getLoanInfo(Pageable pageable, Long userId);
}
