package com.capgemini.userService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesAndTrainingVo {
    private String courseName;
    private String courseDescription;
    private String remarks;
}
