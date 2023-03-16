package com.project.CakeShop.controller;

import com.project.CakeShop.model.User;
import com.project.CakeShop.model.dto.UserLoginDto;
import com.project.CakeShop.model.dto.UserRegisterDto;
import com.project.CakeShop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
        User user = authService.register(userRegisterDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto) throws Exception{
        return ResponseEntity.ok(authService.login(userLoginDto));
    }
}
