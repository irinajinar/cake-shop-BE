package com.project.CakeShop.service.impl;

import com.project.CakeShop.exception.BusinessException;
import com.project.CakeShop.model.User;
import com.project.CakeShop.model.dto.UserLoginDto;
import com.project.CakeShop.model.dto.UserRegisterDto;
import com.project.CakeShop.repository.UserRepository;
import com.project.CakeShop.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Data
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(UserRegisterDto userRegisterDto){
        if(Objects.isNull(userRegisterDto)){
            throw new BusinessException(401, "Body is null");
        }
        if(userRegisterDto.getFirstName().isEmpty()){
            throw new BusinessException(400, "First name can't be empty!");
        }
        if(userRegisterDto.getLastName().isEmpty()){
            throw new BusinessException(400, "Last name, can't be empty");
        }
        if(userRegisterDto.getAddress().isEmpty()){
            throw new BusinessException(400, "Address, must be valid!");
        }
        if(userRegisterDto.getEmail().isEmpty()){
            throw new BusinessException (400, "Email, must be valid");
        }
        if(userRegisterDto.getTelephoneNumber().isEmpty()){
            throw new BusinessException (400, "Telephone number is not valid!");
        }

        if (bCryptPasswordEncoder.encode(userRegisterDto.getPassword()).isEmpty()) {
            throw new BusinessException(400, "Password cannot be null !");
        }
        if(!Objects.isNull(userRepository.findByEmail(userRegisterDto.getEmail()))){
            throw new BusinessException(401,"Email already exist!");
        }

        User user= new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setAddress(userRegisterDto.getAddress());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterDto.getPassword()));
        user.setTelephoneNumber(userRegisterDto.getTelephoneNumber());
        return userRepository.save(user);
    }

    public User login(UserLoginDto userLoginDto) {
        if(Objects.isNull(userLoginDto)){
            throw new BusinessException(401, "Body is null!");
        }
        if(Objects.isNull(userLoginDto.getEmail())){
            throw new BusinessException(400, "Email can't be null!");
        }
        if(Objects.isNull(userLoginDto.getPassword())){
            throw new BusinessException(400, "Password can't be null!");
        }
        User user = userRepository.findByEmail(userLoginDto.getEmail());
        if(Objects.isNull(user)){
            throw new BusinessException(401, "The data entered is not correct!");
        }
        if(!bCryptPasswordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            throw new BusinessException(403, "Email or password is incorrect!");
        }
        return user;
    }
}
