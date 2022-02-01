package com.challenge.alta.service;

import com.challenge.alta.model.UserInfoResponse;
import com.challenge.alta.model.UserRequest;

import java.util.Optional;

public interface UserService {

    public Optional<UserInfoResponse> getUserInfo(long id);

    public void save(UserRequest userRequest);

    public void delete(long id);
}
