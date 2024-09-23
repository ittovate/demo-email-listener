# Customizing your email listener

## You can customize your email listener using these two core interfaces
- IFilter : it is an interface which has a single method which filters an incoming Message from an email 
- IAction : it is an interface which does an action on an incoming Message *e.g: sending a notification*

## By creating your own custom logic you can have a separate filter,action to do whatever you want with the incoming emails

## There are also two core classes but you dont really need to customize them unless you want to listen on a different folder name
- EmailListenerTemplate : which has a single template method that does two things 
  - It starts by filtering out the message using its IFilter
  - Then it does the action after filtering the previous message
```java
    public void filterAndTakeAction(Message message) throws MessagingException {
        Boolean filterResult = emailFilter.filter(message);
        if(filterResult){
            emailAction.doAction(message);
        }
    }   
```
- EmailListener : the class which basically does all the listening part using IMAP and it has a set of EmailListenerTemplate 
  - This is a singleton bean that gets initialized at the start of the application and starts listening immediately
  - To add/remove templates just get its bean using `ApplicationContext.getBean(EmailListener.class)` which will return you its object and then you can use it 
## Steps to create your own customization over the listener
- Create your own IFilter,IAction implementations
  - You can check out `util/filters/DummySubjectFilter` and `util/actions/DummyLoggerAction` as a reference 
  - Then you can create a new `EmailListenerTemplate` which is injected by the filter,action you want this template to use
  - After this add this `EmailListenerTemplate` object to the `EmailListener` object which **You can also remove the template if you want** 
    - Add a template using ```addListenerTemplate(EmailListenerTemplate emailListenerTemplate)``` of the EmailListener bean
    - Remove a template using ```removeListenerTemplate(EmailListenerTemplate emailListenerTemplate)``` of the EmailListener bean
  - You are now listening to the email with the template(or templates) that you added

# NOTE: the current demo has this initialized as a `CommandLineRunner` bean which adds both dummy filters at the beginning but you should have your own logic whether it's initialized using an HTTP request or using a CommandLineRunner bean