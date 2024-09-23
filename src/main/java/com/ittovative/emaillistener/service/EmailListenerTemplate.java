package com.ittovative.emaillistener.service;

import com.ittovative.emaillistener.util.actions.IAction;
import com.ittovative.emaillistener.util.filters.IFilter;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;

public class EmailListenerTemplate {
    private final IFilter emailFilter;
    private final IAction emailAction;

    public EmailListenerTemplate(IFilter emailFilter, IAction emailAction) {
        this.emailFilter = emailFilter;
        this.emailAction = emailAction;
    }

    public void filterAndTakeAction(Message message) throws MessagingException {
        Boolean filterResult = emailFilter.filter(message);
        if(filterResult){
            emailAction.doAction(message);
        }
    }
}
