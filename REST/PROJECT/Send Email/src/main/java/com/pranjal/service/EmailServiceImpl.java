package com.pranjal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranjal.model.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmailAndAttachment(EmailRequest emailRequest, MultipartFile file) throws MessagingException, IOException {
        ObjectMapper objectmapper = new ObjectMapper();
        EmailRequest emailRequest1 = objectmapper.readValue((DataInput) emailRequest, EmailRequest.class);


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("coderkumarshukla@gmail.com", emailRequest.getTitle());
        helper.setTo(emailRequest.getRecipentEmail());
        helper.setSubject(emailRequest.getSubject());
        helper.setText(emailRequest.getBody(), true);

        if (file != null) {
            ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes());
        helper.addAttachment(file.getOriginalFilename(), byteArrayResource);

        }

        mailSender.send(mimeMessage);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailRequest emailRequest) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom("coderkumarshukla@gmail.com", emailRequest.getTitle());
        helper.setTo(emailRequest.getRecipentEmail());
        helper.setSubject(emailRequest.getSubject());
        helper.setText(emailRequest.getBody(), true);

        mailSender.send(mimeMessage);
    }
}
