package com.capgemini.resumeService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalSkill {
    @Column(nullable = false)
    private String skill;

    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;
}
