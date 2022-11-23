package com.capgemini.userService.Exception;

public class UserNameExistException extends  Exception{
    public UserNameExistException() {
        super();
    }

    public UserNameExistException(String message) {
        super(message);
    }
}
