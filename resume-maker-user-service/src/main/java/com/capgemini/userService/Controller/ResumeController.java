package com.capgemini.userService.Controller;

import com.capgemini.userService.Entities.ResumeTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user/Resume")
public class ResumeController {

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/save")
    public ResponseEntity<ResumeTemplateVo> saveResume(@RequestBody ResumeTemplateVo resumeTemplateVo, HttpServletRequest request){
        return new ResponseEntity<>(restTemplate.postForObject("http://RESUME-SERVICE:4050/Resume/save",
                resumeTemplateVo,ResumeTemplateVo.class), HttpStatus.OK);
    }

    @GetMapping("/getInfo/{username}")
    public ResponseEntity<ResumeTemplateVo> getResumeInfo(@PathVariable("username") String username){
        return new ResponseEntity<>(restTemplate.getForObject("http://RESUME-SERVICE:4050/Resume/getResumeInfo/"+username,
                ResumeTemplateVo.class), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResumeTemplateVo> updateResume(@RequestBody ResumeTemplateVo resumeTemplateVo){
        restTemplate.put("http://RESUME-SERVICE:4050/Resume/update",resumeTemplateVo);
        System.out.println(resumeTemplateVo);
        return getResumeInfo(resumeTemplateVo.getUsername());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String > deleteResume(@PathVariable("username") String username){
        restTemplate.delete("http://RESUME-SERVICE:4050/Resume/delete/"+username);
        return new ResponseEntity<>("deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getResume/")
    public ResponseEntity getResume(HttpServletRequest request) {
        String  username =request.getUserPrincipal().getName();
       ByteArrayResource resource = restTemplate.getForObject("http://RESUME-SERVICE:4050/Resume/getResume/"+username,ByteArrayResource.class);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(resource);
    }

    @GetMapping("/hasResume/")
    public ResponseEntity hasResume(HttpServletRequest request) {
        String  username =request.getUserPrincipal().getName();
        Boolean res  = restTemplate.getForObject("http://RESUME-SERVICE:4050/Resume/hasResume/"+username,Boolean.class);
        return ResponseEntity.ok().body(res);
    }

}
