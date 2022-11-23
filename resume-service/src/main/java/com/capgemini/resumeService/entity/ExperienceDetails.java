package com.capgemini.resumeService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceDetails {
    @Column(nullable = false)
    private String  designation;
    @Column(nullable = false)
    private String  companyName;
    private String startDate;
    private String endDate;
    private String remarks;
}
