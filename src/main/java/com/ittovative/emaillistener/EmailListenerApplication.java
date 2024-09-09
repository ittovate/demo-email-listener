package com.ittovative.emaillistener;

import com.ittovative.emaillistener.service.*;
import com.ittovative.emaillistener.util.actions.DummyLoggerAction;
import com.ittovative.emaillistener.util.actions.IAction;
import com.ittovative.emaillistener.util.filters.DummySubjectFilter;
import com.ittovative.emaillistener.util.filters.IFilter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmailListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailListenerApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(ApplicationContext context){
        return args -> {
            IAction action = new DummyLoggerAction();
            IFilter filter = new DummySubjectFilter();
            EmailListenerTemplate dummyEmailListener = new EmailListenerTemplate(filter,action);
            EmailListener emailListener = context.getBean(EmailListener.class);
            emailListener.addListenerTemplate(dummyEmailListener);
        };
    }

}
