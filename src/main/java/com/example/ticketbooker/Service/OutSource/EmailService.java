package com.example.ticketbooker.Service.OutSource;

import java.net.SocketException;

import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@NoArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    public boolean sendEmail(String reciever, String title, String htmlTemplate, Context context) {
        MimeMessage htmlMessage = mailSender.createMimeMessage();
        MimeMessageHelper htmlMail = new MimeMessageHelper(htmlMessage, "utf-8");

        try {

            htmlMail.setFrom(sender);
            htmlMail.setTo(reciever);
            htmlMail.setSubject(title);

            String htmlContent = templateEngine.process(htmlTemplate, context);

            htmlMail.setText(htmlContent, true);

            mailSender.send(htmlMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
