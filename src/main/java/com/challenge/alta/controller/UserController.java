package com.challenge.alta.controller;

import com.challenge.alta.model.UserInfoResponse;
import com.challenge.alta.model.UserRequest;
import com.challenge.alta.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> getUser(@PathVariable(name = "id") long id) {
        try {
            Optional<UserInfoResponse> result = userService.getUserInfo(id);
            return result.map(userInfoResponse -> new ResponseEntity<>(userInfoResponse, HttpStatus.OK)).orElseGet(()
                    -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest) {
        try {
            userService.save(userRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@RequestBody UserRequest userRequest) {
        try {
            userService.delete(userRequest.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
