package com.capgemini.userService.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDetailVo {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String contactNumber;
    private String Address;
    private String linkedinURL;
    private String imageUrl;
}
