package com.challenge.alta.service;

import com.challenge.alta.entity.Loan;
import com.challenge.alta.entity.User;
import com.challenge.alta.model.LoanInfoResponse;
import com.challenge.alta.repository.LoanRepository;
import com.challenge.alta.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private final LoanService loanService = new LoanServiceImpl(loanRepository);

    private Page<Loan> loansMock;

    @BeforeEach
    void loadMockInfo() {
        loansMock = getLoansMock();
    }

    @Test
    void testGetLoanInfoByUserId() {
        when(loanRepository.findByUser_Id(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(loansMock);

        Page<LoanInfoResponse> response = loanService.getLoanInfo(PageRequest.of(0, 5), 1L);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(3, response.getContent().size()),
                () -> assertEquals(response.getContent().get(0).getTotal(), 2200.00)
        );
    }

    @Test
    void testGetLoanInfo() {
        when(loanRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(loansMock);

        Page<LoanInfoResponse> response = loanService.getLoanInfo(PageRequest.of(0, 5), null);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(3, response.getContent().size()),
                () -> assertEquals(response.getContent().get(0).getTotal(), 2200.00)
        );

        verify(loanRepository).findAll(PageRequest.of(0, 5));
    }

    private User getUserMock() {
        User user = new User();
        user.setId(1L);
        return user;
    }

    private Page<Loan> getLoansMock() {
        Loan loan1 = new Loan(1L, 2200.00F, getUserMock());
        Loan loan2 = new Loan(2L, 5200.00F, getUserMock());
        Loan loan3 = new Loan(3L, 7800.00F, getUserMock());
        return new PageImpl<Loan>(Arrays.asList(loan1, loan2, loan3));
    }
}
