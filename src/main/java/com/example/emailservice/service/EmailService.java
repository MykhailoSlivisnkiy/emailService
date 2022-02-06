package com.example.emailservice.service;

import com.example.emailservice.dto.SubscriptionRequest;
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

    public void sendSimpleMessage(SubscriptionRequest subscriptionRequest) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", "Misha");

        String process = templateEngine.process("/new-merchandise", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome to " + subscriptionRequest.getShopName());
        helper.setText(process, true);
        helper.setTo(subscriptionRequest.getDestination());
        javaMailSender.send(mimeMessage);
    }
}