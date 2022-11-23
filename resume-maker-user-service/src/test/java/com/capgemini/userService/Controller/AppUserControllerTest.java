package com.capgemini.userService.Controller;

import com.capgemini.userService.Entities.LoginModel;
import com.capgemini.userService.Entities.RegistrationModel;
import com.capgemini.userService.Entities.Role;
import com.capgemini.userService.Exception.EmailIdExistException;
import com.capgemini.userService.Exception.UserNameExistException;
import com.capgemini.userService.Service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AppUserController.class)
@AutoConfigureMockMvc(addFilters = false)
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService service;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() throws UserNameExistException, EmailIdExistException {
        RegistrationModel model = RegistrationModel.builder().username("abisheks").password("22091995")
                .role(Role.valueOf("USER")).Email("abisheknthn95@gmail.com").contactNumber("9361994840")
                .firstname("abisheks").lastname("nathan").build();
        LoginModel model1 = LoginModel.builder().username("abisheks").password("22091995").build();
        Mockito.when(service.registerUser(model)).thenReturn(true);
        Mockito.when(service.isValidUser(model1)).thenReturn(false);
       // Mockito.when(service.verifyUser("aaabbbccc")).thenReturn(true);
    }

    @Test
    void userLogin() throws Exception {
        mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"username\":\"abisheksridhar\",\n" +
                "    \"password\":\"22091995\"\n" +
                "}")).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    void userRegister() throws Exception {
        mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"abisheksridhar\",\n" +
                        "    \"firstname\":\"abishekanathan\",\n" +
                        "    \"lastname\":\"s\",\n" +
                        "    \"email\":\"abisheknthn95@gmail.com\",\n" +
                        "    \"password\":\"22091995\",\n" +
                        "    \"role\":\"USER\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void userVerification() throws Exception {
        mockMvc.perform(get("/user/verify/aaabbbccc").contentType(MediaType.APPLICATION_JSON)
                .content("")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}