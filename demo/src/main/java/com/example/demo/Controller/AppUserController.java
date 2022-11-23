package com.example.demo.Controller;


import com.example.demo.Entities.AppUser;
import com.example.demo.Entities.LoginModel;
import com.example.demo.Exception.WrongPasswordException;
import com.example.demo.Service.AppUserService;
import com.example.demo.jwt.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JwtUtility utility;

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginModel model) throws WrongPasswordException {
       if(appUserService.isValidUser(model))
       {
           AppUser user = (AppUser)appUserService.loadUserByUsername(model.getUsername());
           return new ResponseEntity<>(utility.generateToken(user), HttpStatus.OK);
       }
       else
           throw new WrongPasswordException("Wrong password");
    }
    @PostMapping("/register")
    public String userRegister()
    {
        return "Register";
    }
}
