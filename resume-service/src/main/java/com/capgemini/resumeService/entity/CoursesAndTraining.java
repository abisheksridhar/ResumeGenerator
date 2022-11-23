package com.capgemini.resumeService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesAndTraining {
    private String courseName;
    private String courseDescription;
    private String remarks;
}
