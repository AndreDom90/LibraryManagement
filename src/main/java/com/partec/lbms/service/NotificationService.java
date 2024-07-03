package com.partec.lbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendReminders() {
        // Logica per inviare promemoria agli utenti
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@example.com");
        message.setSubject("Promemoria Restituzione Libro");
        message.setText("Gentile utente, ricordati di restituire il libro che hai preso in prestito.");
        mailSender.send(message);
    }
}
