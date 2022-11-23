package com.example.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserInformation {

    @Id
    private long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private AppUser appUser;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false,unique = true)
    private String emailId;

    private String contactNumber;

    private Date dateOfBirth;


}
