package com.capgemini.userService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeTemplateVo {
    private String username;
    private String careerObjective;
    private PersonalDetailVo personalDetail;
    private List<EducationDetailsVo> educationDetail;
    private List<ExperienceDetailsVo> experienceDetail;
    private List<TechnicalSkillVo> technicalSkills;
    private List<String> achievements;
    private List<CoursesAndTrainingVo> coursesAndTraining;
    private List<LanguagesKnownVo> languagesKnown;

}
