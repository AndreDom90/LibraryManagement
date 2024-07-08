package com.partec.lbms.service;

import com.partec.lbms.entity.Loan;
import com.partec.lbms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 9 * * ?") // viene eseguito ogni giorno alle 9:00
    public void sendDueDateNotifications() {
        List<Loan> loansDueSoon = loanService.getLoansDueSoon();
        List<User> usersToNotify = loansDueSoon.stream()
                .map(Loan::getUser)
                .distinct()
                .collect(Collectors.toList());

        for (User user : usersToNotify) {
            sendDueDateNotification(user);
        }
    }

    private void sendDueDateNotification(User user) {
        String to = user.getEmail();
        String subject = "Promemoria: Restituzione libro imminente";
        String text = "Gentile " + user.getFirstName() + ",\n\n" +
                "Questo è un promemoria per restituire i libri presi in prestito che stanno per scadere.\n" +
                "Per favore, assicurati di restituirli entro la data di scadenza.\n\n" +
                "Cordiali saluti,\n" +
                "La tua Biblioteca";

        sendEmail(to, subject, text);
    }

    private void sendEmail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); 
        }
    }
}
