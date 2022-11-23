package com.capgemini.userService.Service;

import com.capgemini.userService.Entities.AppUser;
import com.capgemini.userService.Entities.AppUserInformation;
import com.capgemini.userService.Entities.LoginModel;
import com.capgemini.userService.Entities.RegistrationModel;
import com.capgemini.userService.Exception.EmailIdExistException;
import com.capgemini.userService.Exception.UserNameExistException;
import com.capgemini.userService.Repository.AppUserInformationRepository;
import com.capgemini.userService.Repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserInformationRepository appUserInformationRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) appUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User with username:"+username+" not found"));
    }

    @Override
    public boolean isValidUser(LoginModel model) {
        AppUser user = appUserRepository.findByUsername(model.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("User with username:"+model.getUsername()+" not found"));
                return encoder.matches(model.getPassword(), user.getPassword());

    }

    @Override
    public boolean registerUser(RegistrationModel model) throws UserNameExistException, EmailIdExistException {
        if(appUserRepository.findByUsername(model.getUsername()).isPresent())
        {
            throw new UserNameExistException();
        }
        else{
            if(appUserInformationRepository.findByEmailId(model.getEmail()).isPresent()){
                throw new EmailIdExistException();
            }
            AppUserInformation appUserInformation =  new AppUserInformation();
            appUserInformation.setContactNumber(model.getContactNumber());
            appUserInformation.setFirstName(model.getFirstname());
            appUserInformation.setLastName(model.getLastname());
            appUserInformation.setEmailId(model.getEmail());
            log.info(model.getEmail());
            AppUser user = new AppUser();
            user.setUsername(model.getUsername());
            user.setPassword(encoder.encode(model.getPassword()));
            user.setRole(model.getRole());
            user.setUserInformation(appUserInformation);
            user.setIsNonExpired(true);
            user.setIsEnabled(false);
            try{
              user.setVerificationCode(sendVerificationMail(user));
            }catch (Exception e)
            {
               log.info(e.getMessage());
                return false;
            }
            appUserRepository.save(user);
            return true;
        }
    }

    @Override
    public Boolean verifyUser(String username,String verificationString) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(""));
        System.out.println(username+verificationString);
        System.out.println(user.getVerificationCode());
       if(user.getVerificationCode().equals(verificationString)) {
           user.setIsEnabled(true);
           user.setVerificationCode(null);
           appUserRepository.save(user);
           return true;
       }
           else{
           System.out.println("invalid verification code");
               return false;
       }
    }

    public String sendVerificationMail(AppUser user) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getUserInformation().getEmailId();
        log.info(toAddress);
        String from = "resumeGenerator.com";
        String senderName = "abishekanathan.s";
        String registrationUrl = "http://localhost:3000/verifyUser";
        String randomString = RandomString.make(64);
        String username = user.getUsername();
        int length = username.length();
        String Message = "<html><body><h4>Welcome to Resume generator [[name]], </h4><br/>"
                +"Please Click on the below link to verify your Email Address"
                +"<a href='[[url]]'> Verify</a>.<br/>"+"Thank you,<br/>"+
                "ResumeGenerator</body></html>";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom(from);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject("Email Verification");
        Message = Message.replace("[[name]]" , username);
        Message = Message.replace("[[url]]",registrationUrl+"?username="+username+"&token="+randomString);
        messageHelper.setText(Message,true);
        mailSender.send(mimeMessage);
        return  randomString;
    }
}
