package com.ittovative.emaillistener.service;

import jakarta.mail.*;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;
import org.eclipse.angus.mail.imap.IMAPFolder;

import java.util.Set;

import static com.ittovative.emaillistener.constant.ConfigConstants.IMAPS;
import static com.ittovative.emaillistener.constant.ConfigConstants.INBOX_FOLDER;

public class EmailListener extends MessageCountAdapter {
    private final Session session;
    private final String username;
    private final String password;

    private final Set<EmailListenerTemplate> emailListenerTemplates;

    public EmailListener(Session session,
                         String username,
                         String password,
                         Set<EmailListenerTemplate> emailListenerTemplates){
        this.session = session;
        this.username = username;
        this.password = password;
        this.emailListenerTemplates = emailListenerTemplates;
        startListeningInNewThread();
    }
    public void addListenerTemplate(EmailListenerTemplate emailListenerTemplate){
        emailListenerTemplates.add(emailListenerTemplate);
    }
    public void removeListenerTemplate(EmailListenerTemplate emailListenerTemplate){
        emailListenerTemplates.remove(emailListenerTemplate);
    }
    public void startListeningInNewThread() {
        Runnable emailListeningTask = () -> {
            try {
                startListening();
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        };

        Thread listeningThread = new Thread(emailListeningTask);
        listeningThread.start();
    }

    public void startListening() throws MessagingException {
        Store store = session.getStore(IMAPS);
        store.connect(username, password);

        IMAPFolder inboxFolder = (IMAPFolder) store.getFolder(INBOX_FOLDER);
        inboxFolder.open(Folder.READ_ONLY);

        addFolderListener(inboxFolder);

        while (!Thread.interrupted()) {
            try {
                inboxFolder.idle();
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
    }

    private void addFolderListener(IMAPFolder inboxFolder){
        inboxFolder.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent event) {
                Message[] messages = event.getMessages();
                try {
                    for (Message message : messages) {
                        emailListenerTemplates.forEach(emailListenerTemplate -> {
                            try {
                                emailListenerTemplate.filterAndTakeAction(message);
                            } catch (MessagingException exception) {
                                exception.printStackTrace();
                                throw new RuntimeException(exception);
                            }
                        });
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    throw new RuntimeException(exception);
                }
            }
        });
    }

}