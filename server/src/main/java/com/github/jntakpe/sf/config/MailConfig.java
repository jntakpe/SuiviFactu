package com.github.jntakpe.sf.config;

import com.github.jntakpe.sf.config.properties.MailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration de l'envoi de mail
 *
 * @author jntakpe
 */
@Configuration
public class MailConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailConfig.class);

    public static final String PROTOCOL = "smtp";

    private static final String PROP_SMTP_AUTH = "mail.smtp.auth";

    private static final String PROP_STARTTLS = "mail.smtp.starttls.enable";

    private static final String PROP_TRANSPORT_PROTO = "mail.transport.protocol";

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        LOGGER.debug("Configuration de l'envoi de mail");
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailProperties.getHost());
        sender.setPort(mailProperties.getPort());
        sender.setUsername(mailProperties.getUsername());
        sender.setPassword(mailProperties.getPassword());
        Properties sendProperties = new Properties();
        sendProperties.setProperty(PROP_SMTP_AUTH, String.valueOf(mailProperties.isAuth()));
        sendProperties.setProperty(PROP_STARTTLS, String.valueOf(mailProperties.isTls()));
        sendProperties.setProperty(PROP_TRANSPORT_PROTO, PROTOCOL);
        sender.setJavaMailProperties(sendProperties);
        return sender;
    }
}
