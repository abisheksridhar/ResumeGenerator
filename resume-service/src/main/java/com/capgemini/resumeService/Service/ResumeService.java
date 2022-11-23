package com.capgemini.resumeService.Service;


import com.capgemini.resumeService.Exceptions.UserNotFoundException;
import com.capgemini.resumeService.entity.ResumeModel;
import com.itextpdf.text.DocumentException;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;

public interface ResumeService {
    ResumeModel saveResume(ResumeModel model);

    ResumeModel getResume(String username) throws UserNotFoundException;

    ResumeModel updateResume(ResumeModel model) throws UserNotFoundException, CloneNotSupportedException;

    String  deleteResume(String username) throws UserNotFoundException;

    ByteArrayResource generateResume(String username) throws UserNotFoundException, DocumentException, IOException;
}
