package com.project.CakeShop.service;

import com.project.CakeShop.model.User;
import com.project.CakeShop.model.dto.UserLoginDto;
import com.project.CakeShop.model.dto.UserRegisterDto;

public interface AuthService {
    User register(UserRegisterDto userRegisterDto) throws Exception;

    User login(UserLoginDto userLoginDto) throws Exception;
}
