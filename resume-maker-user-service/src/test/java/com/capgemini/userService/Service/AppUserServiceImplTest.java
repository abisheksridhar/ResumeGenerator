package com.capgemini.userService.Service;

import com.capgemini.userService.Entities.AppUser;
import com.capgemini.userService.Entities.AppUserInformation;
import com.capgemini.userService.Entities.LoginModel;
import com.capgemini.userService.Entities.RegistrationModel;
import com.capgemini.userService.Entities.Role;
import com.capgemini.userService.Repository.AppUserInformationRepository;
import com.capgemini.userService.Repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppUserServiceImplTest {

    @MockBean
    private AppUserInformationRepository appUserInformationRepository;

    @Autowired
    private AppUserServiceImpl appUserServiceImpl;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserServiceImpl appUserService;

    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        AppUser appUser = AppUser.builder().userId(1L).username("abisheks").password(encoder.encode("12345678"))
                .role(Role.valueOf("USER")).isEnabled(true).isNonExpired(true).verificationCode("aaabbbccc").build();

        AppUser appUser2 = AppUser.builder().userId(1L).username("abisheks").password(encoder.encode("12345678"))
                .role(Role.valueOf("USER")).isEnabled(false).isNonExpired(true).verificationCode("aaabbbccc").build();
        Mockito.when(appUserRepository.findByUsername("abisheks")).thenReturn(Optional.of(appUser));
        Mockito.when(appUserRepository.findByVerificationCode("aaabbbccc")).thenReturn(Optional.of(appUser2));

    }

    @Test
    void isValidUser() {
        LoginModel model = LoginModel.builder().username("abisheks").password("12345678").build();
        LoginModel model2 = LoginModel.builder().username("abisheks").password("11111111").build();
        assertTrue(appUserService.isValidUser(model));
        assertFalse(appUserService.isValidUser(model2));
    }

    @Test
    void registerUser() {
        RegistrationModel appUser = RegistrationModel.builder().username("abisheks").password("22091995")
                .role(Role.valueOf("USER")).build();
        assertThrows(IllegalStateException.class, () -> appUserService.registerUser(appUser));
    }

    @Test
    void testVerifyUser() {
        AppUserInformation appUserInformation = new AppUserInformation();
        appUserInformation.setLastName("Doe");
        appUserInformation.setDateOfBirth("2020-03-01");
        appUserInformation.setContactNumber("42");
        appUserInformation.setInformationId(123L);
        appUserInformation.setFirstName("Jane");
        appUserInformation.setEmailId("42");

        AppUser appUser = new AppUser();
        appUser.setPassword("iloveyou");
        appUser.setVerificationCode("Verification Code");
        appUser.setIsEnabled(true);
        appUser.setUsername("janedoe");
        appUser.setUserId(123L);
        appUser.setRole(Role.SUBSCRIBER);
        appUser.setUserInformation(appUserInformation);
        appUser.setIsNonExpired(true);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(this.appUserRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertFalse(this.appUserServiceImpl.verifyUser("janedoe", "Verification String"));
        verify(this.appUserRepository).findByUsername((String) any());
    }

    @Test
    void testVerifyUser2() {
        AppUserInformation appUserInformation = new AppUserInformation();
        appUserInformation.setLastName("Doe");
        appUserInformation.setDateOfBirth("2020-03-01");
        appUserInformation.setContactNumber("42");
        appUserInformation.setInformationId(123L);
        appUserInformation.setFirstName("Jane");
        appUserInformation.setEmailId("42");

        AppUser appUser = new AppUser();
        appUser.setPassword("iloveyou");
        appUser.setVerificationCode("Verification String");
        appUser.setIsEnabled(true);
        appUser.setUsername("janedoe");
        appUser.setUserId(123L);
        appUser.setRole(Role.SUBSCRIBER);
        appUser.setUserInformation(appUserInformation);
        appUser.setIsNonExpired(true);
        Optional<AppUser> ofResult = Optional.of(appUser);

        AppUserInformation appUserInformation1 = new AppUserInformation();
        appUserInformation1.setLastName("Doe");
        appUserInformation1.setDateOfBirth("2020-03-01");
        appUserInformation1.setContactNumber("42");
        appUserInformation1.setInformationId(123L);
        appUserInformation1.setFirstName("Jane");
        appUserInformation1.setEmailId("42");

        AppUser appUser1 = new AppUser();
        appUser1.setPassword("iloveyou");
        appUser1.setVerificationCode("Verification Code");
        appUser1.setIsEnabled(true);
        appUser1.setUsername("janedoe");
        appUser1.setUserId(123L);
        appUser1.setRole(Role.SUBSCRIBER);
        appUser1.setUserInformation(appUserInformation1);
        appUser1.setIsNonExpired(true);
        when(this.appUserRepository.save((AppUser) any())).thenReturn(appUser1);
        when(this.appUserRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertTrue(this.appUserServiceImpl.verifyUser("janedoe", "Verification String"));
        verify(this.appUserRepository).findByUsername((String) any());
        verify(this.appUserRepository).save((AppUser) any());
    }

    @Test
    void testVerifyUser3() {
        AppUserInformation appUserInformation = new AppUserInformation();
        appUserInformation.setLastName("Doe");
        appUserInformation.setDateOfBirth("2020-03-01");
        appUserInformation.setContactNumber("42");
        appUserInformation.setInformationId(123L);
        appUserInformation.setFirstName("Jane");
        appUserInformation.setEmailId("42");

        AppUser appUser = new AppUser();
        appUser.setPassword("iloveyou");
        appUser.setVerificationCode("Verification Code");
        appUser.setIsEnabled(true);
        appUser.setUsername("janedoe");
        appUser.setUserId(123L);
        appUser.setRole(Role.SUBSCRIBER);
        appUser.setUserInformation(appUserInformation);
        appUser.setIsNonExpired(true);
        when(this.appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(this.appUserRepository.findByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,
                () -> this.appUserServiceImpl.verifyUser("janedoe", "Verification String"));
        verify(this.appUserRepository).findByUsername((String) any());
    }

    @Test
    void verifyUser() {
//            assertThrows(IllegalStateException.class,()->appUserService.verifyUser("aaabbcbcccc","aaaaaa"));
//            assertTrue(appUserService.verifyUser("aaabbbccc"));
    }

}