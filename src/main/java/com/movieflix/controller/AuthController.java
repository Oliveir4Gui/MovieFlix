package com.movieflix.controller;

import com.movieflix.controller.request.UserRequest;
import com.movieflix.controller.response.UserResponse;
import com.movieflix.entity.User;
import com.movieflix.mapper.UserMapper;
import com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) {
        User newUser = UserMapper.toUser(request);
        userService.saveUser(newUser);
         return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(newUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
     List<User>users = userService.findAllUsers();
     List<UserResponse> userResponseList = users.stream()
             .map(user -> UserMapper.toUserResponse(user)).toList();
     return ResponseEntity.ok(userResponseList);
    }
}
