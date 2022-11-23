package com.example.demo.Service;

import com.capgemini.userService.Entities.LoginModel;
import com.example.demo.Entities.LoginModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    boolean isValidUser(LoginModel model);
}
