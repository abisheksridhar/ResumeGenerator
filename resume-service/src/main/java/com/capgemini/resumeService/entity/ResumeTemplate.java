package com.capgemini.resumeService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeTemplate {
    private String username;
    private String careerObjective;
    private PersonalDetail personalDetail;
    private List<EducationDetails> educationDetails;
    private List<ExperienceDetails> experienceDetails;
    private List<TechnicalSkill> technicalSkill;
    private List<String> achievements;
    private List<CoursesAndTraining> coursesAndTraining;
    private List<LanguagesKnown> languagesKnown;

}
