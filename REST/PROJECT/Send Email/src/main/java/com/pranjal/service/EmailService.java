package com.pranjal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pranjal.model.EmailRequest;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmail(EmailRequest emailRequest) throws Exception, UnsupportedEncodingException;

    void sendEmailAndAttachment(String email, MultipartFile file) throws MessagingException, IOException;

    void sendEmailAndAttachment2(String email, MultipartFile[] file) throws IOException, MessagingException;
}
