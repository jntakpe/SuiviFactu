package com.github.jntakpe.sf.service;

import com.github.jntakpe.sf.config.properties.MailProperties;
import com.github.jntakpe.sf.domain.Utilisateur;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Services gérant les envois de mails
 *
 * @author jntakpe
 */
@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    private MailProperties mailProperties;

    private SpringTemplateEngine springTemplateEngine;

    private JavaMailSenderImpl javaMailSender;

    @Autowired
    public MailService(MailProperties mailProperties, SpringTemplateEngine springTemplateEngine, JavaMailSenderImpl javaMailSender) {
        this.mailProperties = mailProperties;
        this.springTemplateEngine = springTemplateEngine;
        this.javaMailSender = javaMailSender;
    }

    private void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        LOGGER.debug("Envoi du mail {} à {}", subject, to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(mailProperties.getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.warn("Erreur lors de l'envoi du mail {} à {}, exception : {}", subject, to, e.getMessage());
        }
    }

    public void sendActivationEmail(Utilisateur utilisateur, String baseUrl) {
        LOGGER.info("Envoi du mail d'activation à {}", utilisateur.getEmail());
        Context context = new Context();
        context.setVariable("user", utilisateur);
        context.setVariable("baseUrl", baseUrl);
        String content = springTemplateEngine.process("account-activation", context);
        sendEmail(utilisateur.getEmail(), "Activation du compte Suivi de facturation", content, false, true);
    }

    public void sendPasswordResetMail(Utilisateur utilisateur, String baseUrl) {
        LOGGER.info("Envoi d'un nouveau de mot de passe à {}", utilisateur.getEmail());
        Context context = new Context();
        context.setVariable("user", utilisateur);
        context.setVariable("baseUrl", baseUrl);
        String content = springTemplateEngine.process("reset-password", context);
        sendEmail(utilisateur.getEmail(), "Réinitialisation du mot de passe Suivi de facturation", content, false, true);
    }
}
