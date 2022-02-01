package com.challenge.alta.controller;

import com.challenge.alta.model.LoanInfoResponse;
import com.challenge.alta.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping()
    public ResponseEntity<Page<LoanInfoResponse>> getLoans(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                                           @RequestParam(required = false, name = "userId") Long userId) {
        try {
            Page<LoanInfoResponse> page = loanService.getLoanInfo(pageable, userId);
            if(page.hasContent()) {
                return new ResponseEntity<>(page, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
