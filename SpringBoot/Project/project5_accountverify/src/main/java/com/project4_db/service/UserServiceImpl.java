package com.project4_db.service;

import com.project4_db.entity.User;
import com.project4_db.repository.UserRepo;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User saveUser(User user, String url) {
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_USER");

        user.setVerficationCode(UUID.randomUUID().toString());


        User newUser = userRepo.save(user);

        if (newUser != null) {
            sendEmail(newUser, url);
        }

        return newUser;
    }

    @Override
    public void sendEmail(User user, String url) {

        String from = "coderkumarshukla@gmail.com";
        String to = user.getEmail();
        String subject = "Account Verification";
        String content = "Dear [[name]], <br>"+"Please click the link below to verify your registrantion:<br>" + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"+"Thank you, Pranjal Kumar Shukla";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "Kumar");
            helper.setTo(to);
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getFullName());
            String siteUrl = url + "/verify?code=" + user.getVerficationCode();
            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);
            mailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyAccount(String verficationCode) {
        User user  = userRepo.findByVerficationCode(verficationCode);
        if (user == null) {
            return false;
        }
        else {
            user.setEnabled(true);
            user.setVerficationCode(null);

            userRepo.save(user);
            return true;
        }
    }

    @Override
    public void removeSessionMessage(){
        HttpSession session =         ((ServletRequestAttributes)(Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest().getSession();
        session.removeAttribute("msg");
    }
}
