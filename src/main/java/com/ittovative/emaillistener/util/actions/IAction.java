package com.ittovative.emaillistener.util.actions;

import jakarta.mail.Message;

public interface IAction {
    void doAction(Message message);
}
