package com.partec.lbms;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.partec.lbms.entity.Loan;
import com.partec.lbms.entity.User;
import com.partec.lbms.service.LoanService;
import com.partec.lbms.service.NotificationService;
import com.partec.lbms.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.Collections;

public class NotificationServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private LoanService loanService;

    @Mock
    private UserService userService;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendDueDateNotifications() throws MessagingException {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("Test");

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setDueDate(LocalDate.now().plusDays(2));

        when(loanService.getLoansDueSoon()).thenReturn(Collections.singletonList(loan));

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        notificationService.sendDueDateNotifications();

        verify(mailSender, times(1)).send(mimeMessage);
    }

    @Test
    public void testSendDueDateNotification() throws MessagingException {
        User user = new User();
        user.setEmail("user@example.com");
        user.setFirstName("Test");

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        notificationService.sendDueDateNotification(user);

        verify(mailSender, times(1)).send(mimeMessage);
    }
}
