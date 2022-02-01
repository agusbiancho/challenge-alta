package com.challenge.alta;

import com.challenge.alta.entity.Loan;
import com.challenge.alta.entity.User;
import com.challenge.alta.model.LoanInfoResponse;
import com.challenge.alta.model.UserInfoResponse;
import com.challenge.alta.repository.LoanRepository;
import com.challenge.alta.repository.UserRepository;
import com.challenge.alta.service.LoanService;
import com.challenge.alta.service.UserService;
import com.challenge.alta.service.impl.LoanServiceImpl;
import com.challenge.alta.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AltaApplicationTests {

    @MockBean
    UserRepository userRepository;

    @MockBean
    LoanRepository loanRepository;

    @Autowired
    UserService userService;

    @Autowired
    LoanService loanService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        loanRepository = mock(LoanRepository.class);
        userService = new UserServiceImpl(userRepository);
        loanService = new LoanServiceImpl(loanRepository);
    }

    @Test
    void contextLoads() {
        User user = new User(1L, "fname@mail.com", "FirstName", "LastName", null);
        List<Loan> loans = Arrays.asList(
                new Loan(1L, (float) 2200.00, user),
                new Loan(2L, (float) 5200.00, user)
        );
        user.setLoans(loans);


        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserInfoResponse> response = userService.getUserInfo(1L);

        assertTrue(response.isPresent());
        assertEquals("fname@mail.com", response.get().getEmail());
    }

}
