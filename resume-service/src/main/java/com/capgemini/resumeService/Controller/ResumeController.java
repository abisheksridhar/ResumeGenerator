package com.capgemini.resumeService.Controller;

import com.capgemini.resumeService.Exceptions.UserNotFoundException;
import com.capgemini.resumeService.Repository.ResumeRepository;
import com.capgemini.resumeService.Service.ResumeService;
import com.capgemini.resumeService.entity.ResumeModel;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/Resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ResumeRepository resumeRepository;


    @PostMapping("/save")
    public ResponseEntity<ResumeModel> saveResumeInformation(@RequestBody ResumeModel model){
        return  new ResponseEntity<>(resumeService.saveResume(model), HttpStatus.OK);
    }

    @GetMapping("/getResumeInfo/{username}")
    public ResponseEntity<ResumeModel> getResume(@PathVariable("username") String username) throws UserNotFoundException {
        return new ResponseEntity<ResumeModel>(resumeService.getResume(username),HttpStatus.OK);
    }

    @PutMapping("/update")
    public void updateResume(@RequestBody ResumeModel model) throws UserNotFoundException, CloneNotSupportedException {
        System.out.println(model);
        resumeService.updateResume(model);
    }

    @DeleteMapping("/delete/{username}")
    public void deleteResume(@PathVariable("username") String username) throws UserNotFoundException {
        resumeService.deleteResume(username);
    }

    @GetMapping("/getResume/{username}")
    public ByteArrayResource generateResume(@PathVariable("username") String username) throws UserNotFoundException, DocumentException, IOException {
        return resumeService.generateResume(username);
    }

    @GetMapping("/hasResume/{username}")
    public Boolean hasResume(@PathVariable("username") String  username){
        if(resumeRepository.findByUsername(username).isPresent()){
            return true;
        }
        return false;
    }
}
