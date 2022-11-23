package com.capgemini.resumeService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDetail {
    @Column(nullable = false,updatable = false)
    private String firstname;
    private String lastname;
    @Column(nullable = false)
    private String emailAddress;
    @Column(nullable = false)
    private String contactNumber;
    @Column(nullable = false)
    private String address;
    private String linkedinURL;
    private String imageUrl;
}
