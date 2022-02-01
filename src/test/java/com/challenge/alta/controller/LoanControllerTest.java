package com.challenge.alta.controller;

import com.challenge.alta.model.LoanInfoResponse;
import com.challenge.alta.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    private Page<LoanInfoResponse> pageLoanInfoResponseMock;

    @BeforeEach
    void loadInfo() {
        pageLoanInfoResponseMock = getPageLoanInfoResponseMock();
    }

    @Test
    void testGetUserInfoById() throws Exception {
        when(loanService.getLoanInfo(ArgumentMatchers.any(Pageable.class), ArgumentMatchers.anyLong()))
                .thenReturn(pageLoanInfoResponseMock);

        mockMvc.perform(get("/api/loans?page=0&size=5&userId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(loanService).getLoanInfo(PageRequest.of(0, 5), 1L);
    }

    @Test
    void testGetUserInfoByIdNoContent() throws Exception {
        when(loanService.getLoanInfo(ArgumentMatchers.any(Pageable.class), ArgumentMatchers.anyLong()))
                .thenReturn(Page.empty());

        mockMvc.perform(get("/api/loans?page=0&size=5&userId=1"))
                .andExpect(status().isNoContent());

        verify(loanService).getLoanInfo(PageRequest.of(0, 5), 1L);
    }

    @Test
    void testGetUserInfoByIdException() throws Exception {
        when(loanService.getLoanInfo(ArgumentMatchers.any(Pageable.class), ArgumentMatchers.anyLong()))
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/loans?page=0&size=5&userId=1"))
                .andExpect(status().isInternalServerError());

        verify(loanService).getLoanInfo(PageRequest.of(0, 5), 1L);
    }

    private Page<LoanInfoResponse> getPageLoanInfoResponseMock() {
        List<LoanInfoResponse> loans = Arrays.asList(
                new LoanInfoResponse(1L, 2200.00F, 1L),
                new LoanInfoResponse(2L, 5200.00F, 1L),
                new LoanInfoResponse(3L, 7200.00F, 1L)
        );

        return new PageImpl<>(loans);
    }
}
