package com.movieflix.controller;

import com.movieflix.controller.request.LoginRequest;
import com.movieflix.controller.request.UserRequest;
import com.movieflix.controller.response.UserResponse;
import com.movieflix.entity.User;
import com.movieflix.mapper.UserMapper;
import com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) {
        User newUser = UserMapper.toUser(request);
        userService.saveUser(newUser);
         return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(newUser));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User currentUser = (User) authentication.getPrincipal();
    }



    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
     List<User>users = userService.findAllUsers();
     List<UserResponse> userResponseList = users.stream()
             .map(user -> UserMapper.toUserResponse(user)).toList();
     return ResponseEntity.ok(userResponseList);
    }
}
