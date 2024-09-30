package com.ittovative.emaillistener.config;

import com.ittovative.emaillistener.service.EmailListener;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashSet;
import java.util.Properties;

import static com.ittovative.emaillistener.constant.ConfigConstants.*;

@Configuration
public class MailConfig {

    @Value("${mail.host}")
    private String emailHost;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.username}")
    private String emailUsername;

    @Value("${mail.password}")
    private String emailPassword;

    @Bean
    public Session mailSession() {
        Properties props = new Properties();
        props.setProperty(PROTOCOL_PROPERTY, IMAPS);
        props.setProperty(HOST_PROPERTY, emailHost);
        props.setProperty(PORT_PROPERTY, port);

        Session session = Session.getInstance(props);
        session.setDebug(true);

        return session;
    }

    @Bean
    public EmailListener emailListener() {
        return new EmailListener(mailSession(), emailUsername, emailPassword,new HashSet<>());
    }

}
