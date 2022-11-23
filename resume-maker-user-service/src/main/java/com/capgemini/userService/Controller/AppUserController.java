package com.capgemini.userService.Controller;

import com.capgemini.userService.Entities.AppUser;
import com.capgemini.userService.Entities.LoginModel;
import com.capgemini.userService.Entities.RegistrationModel;
import com.capgemini.userService.Entities.Response;
import com.capgemini.userService.Exception.EmailIdExistException;
import com.capgemini.userService.Exception.UserNameExistException;
import com.capgemini.userService.Exception.WrongPasswordException;
import com.capgemini.userService.Service.AppUserService;
import com.capgemini.userService.jwt.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JwtUtility utility;

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody LoginModel model, HttpServletResponse httpResponse) throws WrongPasswordException {
       if(appUserService.isValidUser(model))
       {
           AppUser user = (AppUser)appUserService.loadUserByUsername(model.getUsername());
           return ResponseEntity.status(HttpStatus.OK.value())
                   .body(new Response(200,true,utility.generateToken(user)));
       }
       else {
           throw new WrongPasswordException();
       }
    }
    @PostMapping("/register")
    public ResponseEntity userRegister(@RequestBody RegistrationModel model) throws UserNameExistException, EmailIdExistException {
     return ResponseEntity.status(HttpStatus.OK.value())
             .body(new Response(200,appUserService.registerUser(model),""));
    }

    @GetMapping("/verify")
    public ResponseEntity userVerification(@RequestParam String username,@RequestParam String token) throws UsernameNotFoundException {
       return ResponseEntity.status(200)
               .body(new Response(200, appUserService.verifyUser(username,token), ""));
    }

    @GetMapping("/getUserInfo")
    public  ResponseEntity getUserInfo(HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        return ResponseEntity.status((HttpStatus.OK.value()))
                .body(appUserService.loadUserByUsername(username));
    }
}
