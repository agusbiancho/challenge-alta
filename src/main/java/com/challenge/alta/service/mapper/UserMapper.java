package com.challenge.alta.service.mapper;

import com.challenge.alta.entity.User;
import com.challenge.alta.model.UserInfoResponse;

public class UserMapper {

    public static UserInfoResponse getUserInfo(User user) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setId(user.getId());
        userInfoResponse.setFirstName(user.getFirstName());
        userInfoResponse.setLastName(user.getLastName());
        userInfoResponse.setEmail(user.getEmail());
        userInfoResponse.setLoans(LoanMapper.getLoansFromUserInfo(user.getLoans()));
        return userInfoResponse;
    }
}
