package com.capgemini.resumeService.Service;

import com.capgemini.resumeService.Exceptions.UserNotFoundException;
import com.capgemini.resumeService.Repository.ResumeRepository;
import com.capgemini.resumeService.entity.*;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@Slf4j
public class ResumeServiceImplementation implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Override
    public ResumeModel saveResume(ResumeModel model) {
        return resumeRepository.save(model);
    }

    @Override
    public ResumeModel getResume(String username) throws UserNotFoundException {
        return resumeRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("No Resume found for the given username"));
    }



    @Override
    public ResumeModel updateResume(ResumeModel model) throws UserNotFoundException, CloneNotSupportedException {
        ResumeModel resumeModel = resumeRepository.findByUsername(model.getUsername())
                .orElseThrow(()->new UserNotFoundException("No Resume found for the given username"));
        resumeModel.setAchievements(model.getAchievements());
        resumeModel.setCareerObjective(model.getCareerObjective());
        resumeModel.setTechnicalSkills(model.getTechnicalSkills());
        resumeModel.setCoursesAndTraining(model.getCoursesAndTraining());
        resumeModel.setEducationDetail(model.getEducationDetail());
        resumeModel.setExperienceDetail(model.getExperienceDetail());
        resumeModel.setLanguagesKnown(model.getLanguagesKnown());
        resumeModel.setPersonalDetail(model.getPersonalDetail());
        return resumeRepository.save(resumeModel);
    }

    @Override
    public String deleteResume(String username) throws UserNotFoundException {
        ResumeModel model =resumeRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("USer not found"));
         resumeRepository.delete(model);
         return "Deleted Successfully";
    }

    @Override
    public ByteArrayResource generateResume(String username) throws UserNotFoundException, IOException, DocumentException {
        ResumeModel model = getResume(username);
        String path ="C:/Users/ABISHEKS/Desktop/resumeGenerator/resume-service/src/main/resources/";
        String fileName = username+".pdf";
        String savePath = path+fileName;
        String resumeHTML = doGenerateHTML(model);
        HtmlConverter.convertToPdf(resumeHTML,new FileOutputStream(savePath));
        Path filePath = Paths.get(savePath);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));

        return resource;
}

    private String doGenerateHTML(ResumeModel model) throws DocumentException {
        String educationDetail = "";
        String experienceDetail = "";
        String achievementList ="";
        String  Skill ="";
        String courses ="";
        String languagesKnown ="";
        for(EducationDetails details: model.getEducationDetail()){
            if(details.getEndDate() == null)
                details.setEndDate("pursuing");
            educationDetail = educationDetail+"<b>"+details.getQualification()+"</b><br/>"+
                     details.getUniversityName() +"<br/>"+ details.getStartDate()+"-"+ details.getEndDate();
        }
        for(ExperienceDetails details: model.getExperienceDetail()){
            if(details.getEndDate() == null)
                details.setEndDate("Currently Working");
            experienceDetail = experienceDetail + "<b>"+details.getDesignation()+"</b><br/>"+
                    details.getCompanyName() +"<br/>"+ details.getStartDate()+" - "+details.getEndDate();
        }
        for(String achievement: model.getAchievements()){
            achievementList = achievementList +"<li>"+achievement+"</li>";
        }
        String achievements = "<ul> "+ achievementList +"</ul>";
        for(TechnicalSkill skill: model.getTechnicalSkills()){
            Skill = Skill+"<li>"+skill.getSkill()+"  -  "+skill.getProficiency()+"</li>";

        }
        String technicalSkill = "<ul>"+Skill+"</ul>";
        for(CoursesAndTraining coursesAndTraining: model.getCoursesAndTraining()){
            courses  = courses+"<li>"+"<b>"+coursesAndTraining.getCourseName()+"</b><br/>"+
                    coursesAndTraining.getCourseDescription()+"</li>";
        }
        String courseAndTraining = "<ul>"+courses+"</ul>";
        for(LanguagesKnown Known: model.getLanguagesKnown()){
            languagesKnown = languagesKnown+"<li>"+Known.getLanguageName()+ " - "+ Known.getProficiency()+"</li>";
        }
        String languages = "<ul>"+languagesKnown+"</ul>";
        log.info(String.valueOf(model.getAchievements().isEmpty()));
        String html = "<html>" +
                "    <body>" +
                "        <header>" +
                "        <article>" +
                           model.getPersonalDetail().getFirstname()+" "+ model.getPersonalDetail().getLastname() +"<br/>"+
                            model.getPersonalDetail().getEmailAddress()+"<br/>"+
                "            <address>"+model.getPersonalDetail().getAddress()+"</address>" +
                "contact:"+model.getPersonalDetail().getContactNumber()+
                "        </article>" +
                "        </header>" +
                "        <hr/>" +
                "        <b>CareerObjective</b><br>"+ "<p>"+model.getCareerObjective()+"</p><hr/>"+
                "<b>Education</b><br/>"+educationDetail+  "<hr/>" +
                "<b>Experience</b><br/>"+experienceDetail+  "<hr/>" ;
        if(!model.getTechnicalSkills().isEmpty())
            html = html+"<b>Technical Skills</b><br/>"+technicalSkill+  "        <hr/>";
        if(!model.getAchievements().isEmpty())
                html = html+"<b>Achievements</b><br/>"+achievements+ "<hr/>";
        if(!model.getCoursesAndTraining().isEmpty())
            html = html+"<b>Courses and Training</b><br/>"+courseAndTraining+ "<hr/>";
        if(!model.getLanguagesKnown().isEmpty())
            html = html+"<b>Languages </b><br/>"+languages;
        return html;
    }
    }
