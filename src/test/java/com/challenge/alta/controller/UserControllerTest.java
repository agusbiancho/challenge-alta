package com.challenge.alta.controller;

import com.challenge.alta.model.LoanInfoResponse;
import com.challenge.alta.model.UserInfoResponse;
import com.challenge.alta.model.UserRequest;
import com.challenge.alta.repository.UserRepository;
import com.challenge.alta.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    UserInfoResponse userInfoResponse;
    UserRequest userRequest;
    ObjectMapper objectMapper;

    @BeforeEach
    void loadInfo() {
        userInfoResponse = getUserInfoResponseMock();
        userRequest = getUserRequestMock();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetUserInfoById() throws Exception {
        when(userService.getUserInfo(ArgumentMatchers.anyLong())).thenReturn(Optional.of(userInfoResponse));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("fname@mail.com"));

        verify(userService).getUserInfo(1L);
    }

    @Test
    void testGetUserInfoByIdNoContent() throws Exception {
        when(userService.getUserInfo(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService).getUserInfo(1L);
    }

    @Test
    void testGetUserInfoByIdException() throws Exception {
        when(userService.getUserInfo(ArgumentMatchers.anyLong())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isInternalServerError());

        verify(userService).getUserInfo(1L);
    }

    @Test
    void testAddUser() throws Exception {
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userRequest)))
                .andExpect(status().isOk());
    }

    private UserInfoResponse getUserInfoResponseMock() {
        List<LoanInfoResponse> loans = Arrays.asList(
                new LoanInfoResponse(1L, (float) 2200.00, 1L),
                new LoanInfoResponse(2L, (float) 5200.00, 1L));
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setId(1L);
        userInfoResponse.setFirstName("FirstName");
        userInfoResponse.setLastName("LastName");
        userInfoResponse.setEmail("fname@mail.com");
        userInfoResponse.setLoans(loans);

        return userInfoResponse;
    }

    private UserRequest getUserRequestMock() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(1L);
        userRequest.setFirstName("FirstName");
        userRequest.setLastName("LastName");
        userRequest.setEmail("fname@gmail.com");
        return userRequest;
    }
}
