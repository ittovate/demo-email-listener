package com.ittovative.emaillistener.util.filters;


import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class DummySubjectFilter implements IFilter {
    @Override
    public Boolean filter(Message message) throws MessagingException {
        return message.getSubject().length() > 3;
    }
}
