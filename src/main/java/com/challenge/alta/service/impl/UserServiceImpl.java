package com.challenge.alta.service.impl;

import com.challenge.alta.entity.User;
import com.challenge.alta.model.UserInfoResponse;
import com.challenge.alta.model.UserRequest;
import com.challenge.alta.repository.UserRepository;
import com.challenge.alta.service.UserService;
import com.challenge.alta.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserInfoResponse> getUserInfo(long id) {
        try {
            Optional<User> userDB = userRepository.findById(id);
            if(userDB.isPresent()) {
                User user = userDB.get();
                return Optional.of(UserMapper.getUserInfo(user));
            }
            return Optional.empty();
        } catch (Exception e) {
            log.error("Exception getUserInfo() by id: " + id);
            log.error("Exception message: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void save(UserRequest userRequest) {
        try {
            User user = new User();
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());

            userRepository.save(user);
        } catch (Exception e) {
            log.error("Exception addUser() by request: " + userRequest);
            log.error("Exception message: " + e.getMessage());
            throw new RuntimeException("Cann't create user.");
        }
    }

    @Override
    public void delete(long id) {
        try {
            Optional<User> u = userRepository.findById(id);
            if (u.isPresent()) {
                userRepository.delete(u.get());
            } else {
                log.error("Not found user by id: " + id);
                throw new RuntimeException("Not found user");
            }
        } catch (Exception e) {
            log.error("Exception deleteUser() by id: " + id);
            log.error("Exception message: " + e.getMessage());
            throw e;
        }
    }
}