package com.capgemini.userService.Repository;

import com.capgemini.userService.Entities.AppUser;
import com.capgemini.userService.Entities.AppUserInformation;
import com.capgemini.userService.Entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PasswordEncoder encoder;

    @BeforeEach
    @Transactional
    void setUp() {

        AppUserInformation information = AppUserInformation.builder().informationId(1L).contactNumber("9361994840")
                .emailId("abisheknthn95@gmail.com").firstName("abishek").build();
        AppUser appUser = AppUser.builder().userId(1L).username("abisheks").password(encoder.encode("12345678"))
                .role(Role.valueOf("USER")).isEnabled(true).isNonExpired(true).userInformation(information).verificationCode("aaabbbccc")
                .build();

        entityManager.merge(information);
        entityManager.merge(appUser);
    }

    @Test
    void findByUsernameTest() {
        AppUser user = appUserRepository.findByUsername("abisheks").get();
        assertTrue(encoder.matches("12345678",user.getPassword()));
    }

    @Test
    void findByVerificationCode() {
        AppUser user = appUserRepository.findByVerificationCode("aaabbbccc").get();
        assertTrue(user.getIsEnabled());
    }
}