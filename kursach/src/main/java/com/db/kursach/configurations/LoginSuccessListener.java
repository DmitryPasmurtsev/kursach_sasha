package com.db.kursach.configurations;

import com.db.kursach.controllers.AppController;
import com.db.kursach.models.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.authentication.eventmain.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final AppController appController;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent evt) {
        appController.user = (User) evt.getAuthentication().getPrincipal();
        System.out.println(appController.user.getUsername() + " has just logged in");

    }
}
