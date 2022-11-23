package com.capgemini.userService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguagesKnownVo {

    private String languageName;
    @Enumerated(EnumType.STRING)
    private ProficiencyVo proficiency;


}
