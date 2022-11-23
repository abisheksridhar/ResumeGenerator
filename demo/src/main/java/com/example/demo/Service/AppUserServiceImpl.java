package com.example.demo.Service;

import com.capgemini.userService.Entities.AppUser;
import com.capgemini.userService.Entities.LoginModel;
import com.capgemini.userService.Repository.AppUserRepository;
import com.example.demo.Entities.LoginModel;
import com.example.demo.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) appUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User with username:"+username+" not found"));
    }

    @Override
    public boolean isValidUser(LoginModel model) {
        AppUser user = appUserRepository.findByUsername(model.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("User with username:"+model.getUsername()+" not found"));
                return encoder.matches(model.getPassword(), user.getPassword());

    }

    @Override
    public boolean isValidUser(LoginModel model) {
        return false;
    }
}
