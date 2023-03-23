package com.project.CakeShop.service;

import com.project.CakeShop.model.User;
import com.project.CakeShop.model.dto.UserLoginDto;
import com.project.CakeShop.repository.CartRepository;
import com.project.CakeShop.repository.UserRepository;
import com.project.CakeShop.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private User user;

    @Test
    void successfulLogin() {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("ada@gmail.com");
        userLoginDto.setPassword("123");
        doReturn(user).when(userRepository).findByEmail(userLoginDto.getEmail());
        doReturn(true).when(bCryptPasswordEncoder).matches(any(), any());
        authService.login(userLoginDto);
    }
}
