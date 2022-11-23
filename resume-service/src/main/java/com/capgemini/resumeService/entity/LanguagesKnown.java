package com.capgemini.resumeService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguagesKnown {

    private String languageName;
    private boolean canRead;
    private boolean canWrite;
    private boolean canSpeak;
    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;


}
