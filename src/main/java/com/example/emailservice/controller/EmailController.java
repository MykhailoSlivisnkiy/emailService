package com.example.emailservice.controller;

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
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @GetMapping
    public @ResponseBody ResponseEntity sendSimpleEmail(@RequestParam String email) {

        try {
            emailService.sendSimpleMessage(email);
        } catch (MailException | MessagingException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
