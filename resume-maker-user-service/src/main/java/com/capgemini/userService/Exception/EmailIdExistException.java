package com.capgemini.userService.Exception;

public class EmailIdExistException extends  Exception{
    public EmailIdExistException() {
        super();
    }

    public EmailIdExistException(String message) {
        super(message);
    }
}
