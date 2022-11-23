package com.capgemini.userService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalSkillVo {
    @Column(nullable = false)
    private String skill;

    @Enumerated(EnumType.STRING)
    private ProficiencyVo proficiency;
}
