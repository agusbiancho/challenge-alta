package com.challenge.alta.service.impl;

import com.challenge.alta.entity.Loan;
import com.challenge.alta.entity.User;
import com.challenge.alta.model.LoanInfoResponse;
import com.challenge.alta.repository.LoanRepository;
import com.challenge.alta.service.LoanService;
import com.challenge.alta.service.mapper.LoanMapper;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class LoanServiceImpl implements LoanService {

    private static final Logger log = getLogger(LoanServiceImpl.class);

    private LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Page<LoanInfoResponse> getLoanInfo(Pageable pageable, Long userId) {
        try {
            if (userId != null) {
                return loanRepository.findByUser_Id(userId, pageable).map(LoanMapper::getLoan);
            } else {
                return loanRepository.findAll(pageable).map(LoanMapper::getLoan);
            }
        } catch (Exception e) {
            if (userId != null) {
                log.error("Exception getUserInfo() by id: " + userId);
            } else {
                log.error("Exception getUserInfo():");
            }
            log.error("Exception message: " + e.getMessage());
            throw e;
        }
    }
}
