package com.capgemini.resumeService.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.capgemini.resumeService.Exceptions.UserNotFoundException;
import com.capgemini.resumeService.Repository.ResumeRepository;
import com.capgemini.resumeService.entity.PersonalDetail;
import com.capgemini.resumeService.entity.ResumeModel;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ResumeServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class ResumeServiceImplementationTest {
    @MockBean
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeServiceImplementation resumeServiceImplementation;

    @Test
    void testGetResume() throws UserNotFoundException {
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
        Optional<ResumeModel> ofResult = Optional.of(resumeModel);
        when(this.resumeRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(resumeModel, this.resumeServiceImplementation.getResume("janedoe"));
        verify(this.resumeRepository).findByUsername((String) any());
    }

    @Test
    void testGetResume2() throws UserNotFoundException {
        when(this.resumeRepository.findByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> this.resumeServiceImplementation.getResume("janedoe"));
        verify(this.resumeRepository).findByUsername((String) any());
    }

    @Test
    void testGetResume3() throws UserNotFoundException {
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
        Optional<ResumeModel> ofResult = Optional.of(resumeModel);
        when(this.resumeRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(resumeModel, this.resumeServiceImplementation.getResume("janedoe"));
        verify(this.resumeRepository).findByUsername((String) any());
    }

    @Test
    void testGetResume4() throws UserNotFoundException {
        when(this.resumeRepository.findByUsername((String) any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> this.resumeServiceImplementation.getResume("janedoe"));
        verify(this.resumeRepository).findByUsername((String) any());
    }
}

