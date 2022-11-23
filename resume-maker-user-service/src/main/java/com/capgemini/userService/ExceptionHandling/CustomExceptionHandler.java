package com.capgemini.userService.ExceptionHandling;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.capgemini.userService.Entities.Response;
import com.capgemini.userService.Exception.EmailIdExistException;
import com.capgemini.userService.Exception.UserNameExistException;
import com.capgemini.userService.Exception.WrongPasswordException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@ResponseStatus
public class CustomExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity userNameNotFoundResponse(){
        return ResponseEntity.status(200)
                .body(new Response(1000,false,"Username not found"));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity WrongPasswordResponse(){
        return ResponseEntity.status(200)
                .body(new Response(1001,false,"Wrong Password"));
    }
    @ExceptionHandler(UserNameExistException.class)
    public ResponseEntity UserNameExistResponse(){
        return ResponseEntity.status(200)
                .body(new Response(1002,false,"Username Exist try different username"));
    }
    @ExceptionHandler(EmailIdExistException.class)
    public ResponseEntity EmailIdExistEResponse(){
        return ResponseEntity.status(200)
                .body(new Response(1003,false,"Email Id Exist try login !"));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity response(Exception e)
    {
        return ResponseEntity.status(500)
                .body(new Response(500,false,e.getMessage()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity tokenExpiredResponse(Exception e){
        return ResponseEntity.status(401)
                .body(new Response(2000,false,e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableResponse(Exception e){
            return ResponseEntity.status(200)
                    .body(new Response(2001,false,e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity IllegalStateExceptionResponse(Exception e){
        return ResponseEntity.status(200)
                .body(new Response(3001,false,e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationExceptionResponse(Exception e){
        return ResponseEntity.status(200)
                .body(new Response(2003,false,e.getMessage()));
    }
}
