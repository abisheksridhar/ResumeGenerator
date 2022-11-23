package com.capgemini.userService.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationModel {
    private  String username;
    private  String firstname;
    private  String lastname;
    private  String Email;
    private  String password;
    private  String contactNumber;
    private  String dateOfBirth;
    private  Role role;
}
