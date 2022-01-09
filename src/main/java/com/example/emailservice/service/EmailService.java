package com.example.emailservice.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public void sendSimpleMessage(String to) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", "Misha");

        String process = templateEngine.process("/new-merchandise", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + to);
        helper.setText(process, true);
        helper.setTo(to);
        javaMailSender.send(mimeMessage);
    }
}