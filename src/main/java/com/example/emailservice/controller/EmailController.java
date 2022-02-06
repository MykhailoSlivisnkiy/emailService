package com.example.emailservice.controller;

import com.example.emailservice.dto.SubscriptionRequest;
import com.example.emailservice.service.EmailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/subscribe")
@AllArgsConstructor
public class EmailController {

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping
    public @ResponseBody ResponseEntity sendSimpleEmail(@RequestBody SubscriptionRequest subscriptionRequest) throws MessagingException {

            emailService.sendSimpleMessage(subscriptionRequest);

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
