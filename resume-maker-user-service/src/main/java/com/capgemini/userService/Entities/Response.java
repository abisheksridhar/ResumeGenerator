package com.capgemini.userService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Response {
    private int status;
    private boolean Response;
    private String message;
}
