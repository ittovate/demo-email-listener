package com.ittovative.emaillistener.util.actions;

import jakarta.mail.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class DummyLoggerAction implements IAction {
    private final Logger logger = LoggerFactory.getLogger(DummyLoggerAction.class);

    @Override
    public void doAction(Message message) {
        logger.info("Logging the message! " + message);
    }
}
