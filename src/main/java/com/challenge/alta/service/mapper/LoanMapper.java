package com.challenge.alta.service.mapper;

import com.challenge.alta.entity.Loan;
import com.challenge.alta.model.LoanInfoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class LoanMapper {

    public static List<LoanInfoResponse> getLoansFromUserInfo(List<Loan> loans) {
        return loans.stream().map(LoanMapper::getLoan).collect(Collectors.toList());
    }

    public static LoanInfoResponse getLoan(Loan loan) {
        LoanInfoResponse loanInfoResponse = new LoanInfoResponse();
        loanInfoResponse.setId(loan.getId());
        loanInfoResponse.setTotal(loan.getTotal());
        loanInfoResponse.setUserId(loan.getUser().getId());
        return loanInfoResponse;
    }
}
