package com.capgemini.userService.Service;

import com.capgemini.userService.Entities.LoginModel;
import com.capgemini.userService.Entities.RegistrationModel;
import com.capgemini.userService.Exception.EmailIdExistException;
import com.capgemini.userService.Exception.UserNameExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {

    boolean isValidUser(LoginModel model);

    boolean registerUser(RegistrationModel model) throws UserNameExistException, EmailIdExistException;

    Boolean verifyUser(String username, String verificationString);
}
