package com.challenge.alta.service;

import com.challenge.alta.entity.Loan;
import com.challenge.alta.entity.User;
import com.challenge.alta.model.UserInfoResponse;
import com.challenge.alta.model.UserRequest;
import com.challenge.alta.repository.UserRepository;
import com.challenge.alta.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private final UserService userService = new UserServiceImpl(userRepository);

    private User userMock;
    private List<Loan> loansMock;
    private UserRequest userRequestMock;

    @BeforeEach
    void loadInfoMock() {
        userMock = getUserMock();
        loansMock = getLoansMock();
        userRequestMock = getUserRequestMock();
    }

    @Test
    void testGetUserInfo() {
        userMock.setLoans(loansMock);
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(userMock));

        Optional<UserInfoResponse> userInfoResponse = userService.getUserInfo(1L);

        assertTrue(userInfoResponse.isPresent());
        UserInfoResponse response = userInfoResponse.get();

        assertAll(
                () -> assertEquals("FirstName", response.getFirstName()),
                () -> assertEquals(3, response.getLoans().size())
        );

        verify(userRepository).findById(1L);
    }

    @Test
    void testAddUser() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userMock);

        userService.save(userRequestMock);
    }

    @Test
    void testAddUserException() {
        when(userRepository.save(ArgumentMatchers.any(User.class)))
                .thenThrow(new RuntimeException("Cann't create user."));
        Exception exception = assertThrows(RuntimeException.class, () -> userService.save(userRequestMock));

        assertEquals("Cann't create user.", exception.getMessage());
    }

    @Test
    void testDeleteUserException() {
        Exception exception = assertThrows(RuntimeException.class, () -> userService.delete(10L));

        assertEquals("Not found user", exception.getMessage());
    }

    private User getUserMock() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setEmail("fname@gmail.com");
        return user;
    }

    private UserRequest getUserRequestMock() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(1L);
        userRequest.setFirstName("FirstName");
        userRequest.setLastName("LastName");
        userRequest.setEmail("fname@gmail.com");
        return userRequest;
    }

    private List<Loan> getLoansMock() {
        Loan loan1 = new Loan(1L, (float) 2200.00, getUserMock());
        Loan loan2 = new Loan(2L, (float) 5200.00, getUserMock());
        Loan loan3 = new Loan(3L, (float) 7800.00, getUserMock());
        return Arrays.asList(loan1, loan2, loan3);
    }
}
