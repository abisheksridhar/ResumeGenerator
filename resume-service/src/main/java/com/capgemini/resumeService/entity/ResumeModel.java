package com.capgemini.resumeService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.util.Cloneable;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeModel extends Cloneable {


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_id_generator")
    @SequenceGenerator(name = "resume_id_generator", sequenceName = "resume_id", allocationSize = 1)
    @Column(name = "resume_id", nullable = false)
    private Long resumeId;

    @Column(nullable = false,updatable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String careerObjective;

    @Embedded
    private PersonalDetail personalDetail;

    @Embedded
    @ElementCollection
    private List<EducationDetails> educationDetail;

    @Embedded
    @ElementCollection
    private List<ExperienceDetails> experienceDetail;

    @Embedded
    @ElementCollection
    private List<TechnicalSkill> technicalSkills;

    @ElementCollection
    private List<String> achievements;

    @Embedded
    @ElementCollection
    private List<CoursesAndTraining> coursesAndTraining;

    @Embedded
    @ElementCollection
    private List<LanguagesKnown> languagesKnown;

}
