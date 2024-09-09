package com.ittovative.emaillistener.util.filters;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;

public interface IFilter {
    Boolean filter(Message message) throws MessagingException;
}