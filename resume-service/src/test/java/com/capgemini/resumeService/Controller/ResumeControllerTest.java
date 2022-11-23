package com.capgemini.resumeService.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.capgemini.resumeService.Repository.ResumeRepository;
import com.capgemini.resumeService.Service.ResumeService;
import com.capgemini.resumeService.entity.PersonalDetail;
import com.capgemini.resumeService.entity.ResumeModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ResumeController.class})
@ExtendWith(SpringExtension.class)
class ResumeControllerTest {
    @Autowired
    private ResumeController resumeController;

    @MockBean
    private ResumeRepository resumeRepository;

    @MockBean
    private ResumeService resumeService;

    @Test
    void testGetResume() throws Exception {
        PersonalDetail personalDetail = new PersonalDetail();
        personalDetail.setLinkedinURL("https://example.org/example");
        personalDetail.setContactNumber("42");
        personalDetail.setImageUrl("https://example.org/example");
        personalDetail.setFirstname("Jane");
        personalDetail.setAddress("42 Main St");
        personalDetail.setLastname("Doe");
        personalDetail.setEmailAddress("42 Main St");

        ResumeModel resumeModel = new ResumeModel();
        resumeModel.setCoursesAndTraining(new ArrayList<>());
        resumeModel.setUsername("janedoe");
        resumeModel.setAchievements(new ArrayList<>());
        resumeModel.setPersonalDetail(personalDetail);
        resumeModel.setLanguagesKnown(new ArrayList<>());
        resumeModel.setEducationDetail(new ArrayList<>());
        resumeModel.setResumeId(123L);
        resumeModel.setCareerObjective("Career Objective");
        resumeModel.setExperienceDetail(new ArrayList<>());
        resumeModel.setTechnicalSkills(new ArrayList<>());
        when(this.resumeService.getResume((String) any())).thenReturn(resumeModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Resume/getResumeInfo/{username}",
                "janedoe");
        MockMvcBuilders.standaloneSetup(this.resumeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"resumeId\":123,\"username\":\"janedoe\",\"careerObjective\":\"Career Objective\",\"personalDetail\":{\"firstname"
                                        + "\":\"Jane\",\"lastname\":\"Doe\",\"emailAddress\":\"42 Main St\",\"contactNumber\":\"42\",\"address\":\"42 Main"
                                        + " St\",\"linkedinURL\":\"https://example.org/example\",\"imageUrl\":\"https://example.org/example\"},\"educationDetail"
                                        + "\":[],\"experienceDetail\":[],\"technicalSkills\":[],\"achievements\":[],\"coursesAndTraining\":[],\"languagesKnown"
                                        + "\":[]}"));
    }

    @Test
    void testUpdateResume() throws Exception {
        PersonalDetail personalDetail = new PersonalDetail();
        personalDetail.setLinkedinURL("https://example.org/example");
        personalDetail.setContactNumber("42");
        personalDetail.setImageUrl("https://example.org/example");
        personalDetail.setFirstname("Jane");
        personalDetail.setAddress("42 Main St");
        personalDetail.setLastname("Doe");
        personalDetail.setEmailAddress("42 Main St");

        ResumeModel resumeModel = new ResumeModel();
        resumeModel.setCoursesAndTraining(new ArrayList<>());
        resumeModel.setUsername("janedoe");
        resumeModel.setAchievements(new ArrayList<>());
        resumeModel.setPersonalDetail(personalDetail);
        resumeModel.setLanguagesKnown(new ArrayList<>());
        resumeModel.setEducationDetail(new ArrayList<>());
        resumeModel.setResumeId(123L);
        resumeModel.setCareerObjective("Career Objective");
        resumeModel.setExperienceDetail(new ArrayList<>());
        resumeModel.setTechnicalSkills(new ArrayList<>());
        when(this.resumeService.updateResume((ResumeModel) any())).thenReturn(resumeModel);

        PersonalDetail personalDetail1 = new PersonalDetail();
        personalDetail1.setLinkedinURL("https://example.org/example");
        personalDetail1.setContactNumber("42");
        personalDetail1.setImageUrl("https://example.org/example");
        personalDetail1.setFirstname("Jane");
        personalDetail1.setAddress("42 Main St");
        personalDetail1.setLastname("Doe");
        personalDetail1.setEmailAddress("42 Main St");

        ResumeModel resumeModel1 = new ResumeModel();
        resumeModel1.setCoursesAndTraining(new ArrayList<>());
        resumeModel1.setUsername("janedoe");
        resumeModel1.setAchievements(new ArrayList<>());
        resumeModel1.setPersonalDetail(personalDetail1);
        resumeModel1.setLanguagesKnown(new ArrayList<>());
        resumeModel1.setEducationDetail(new ArrayList<>());
        resumeModel1.setResumeId(123L);
        resumeModel1.setCareerObjective("Career Objective");
        resumeModel1.setExperienceDetail(new ArrayList<>());
        resumeModel1.setTechnicalSkills(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(resumeModel1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/Resume/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.resumeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSaveResumeInformation() throws Exception {
        PersonalDetail personalDetail = new PersonalDetail();
        personalDetail.setLinkedinURL("https://example.org/example");
        personalDetail.setContactNumber("42");
        personalDetail.setImageUrl("https://example.org/example");
        personalDetail.setFirstname("Jane");
        personalDetail.setAddress("42 Main St");
        personalDetail.setLastname("Doe");
        personalDetail.setEmailAddress("42 Main St");

        ResumeModel resumeModel = new ResumeModel();
        resumeModel.setCoursesAndTraining(new ArrayList<>());
        resumeModel.setUsername("janedoe");
        resumeModel.setAchievements(new ArrayList<>());
        resumeModel.setPersonalDetail(personalDetail);
        resumeModel.setLanguagesKnown(new ArrayList<>());
        resumeModel.setEducationDetail(new ArrayList<>());
        resumeModel.setResumeId(123L);
        resumeModel.setCareerObjective("Career Objective");
        resumeModel.setExperienceDetail(new ArrayList<>());
        resumeModel.setTechnicalSkills(new ArrayList<>());
        when(this.resumeService.saveResume((ResumeModel) any())).thenReturn(resumeModel);

        PersonalDetail personalDetail1 = new PersonalDetail();
        personalDetail1.setLinkedinURL("https://example.org/example");
        personalDetail1.setContactNumber("42");
        personalDetail1.setImageUrl("https://example.org/example");
        personalDetail1.setFirstname("Jane");
        personalDetail1.setAddress("42 Main St");
        personalDetail1.setLastname("Doe");
        personalDetail1.setEmailAddress("42 Main St");

        ResumeModel resumeModel1 = new ResumeModel();
        resumeModel1.setCoursesAndTraining(new ArrayList<>());
        resumeModel1.setUsername("janedoe");
        resumeModel1.setAchievements(new ArrayList<>());
        resumeModel1.setPersonalDetail(personalDetail1);
        resumeModel1.setLanguagesKnown(new ArrayList<>());
        resumeModel1.setEducationDetail(new ArrayList<>());
        resumeModel1.setResumeId(123L);
        resumeModel1.setCareerObjective("Career Objective");
        resumeModel1.setExperienceDetail(new ArrayList<>());
        resumeModel1.setTechnicalSkills(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(resumeModel1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Resume/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.resumeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"resumeId\":123,\"username\":\"janedoe\",\"careerObjective\":\"Career Objective\",\"personalDetail\":{\"firstname"
                                        + "\":\"Jane\",\"lastname\":\"Doe\",\"emailAddress\":\"42 Main St\",\"contactNumber\":\"42\",\"address\":\"42 Main"
                                        + " St\",\"linkedinURL\":\"https://example.org/example\",\"imageUrl\":\"https://example.org/example\"},\"educationDetail"
                                        + "\":[],\"experienceDetail\":[],\"technicalSkills\":[],\"achievements\":[],\"coursesAndTraining\":[],\"languagesKnown"
                                        + "\":[]}"));
    }
}

