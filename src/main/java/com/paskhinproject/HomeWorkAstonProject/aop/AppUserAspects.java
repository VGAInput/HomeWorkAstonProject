package com.paskhinproject.HomeWorkAstonProject.aop;

import com.paskhinproject.HomeWorkAstonProject.models.AppUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

@Aspect
@Component
public class AppUserAspects {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution( * com.paskhinproject.HomeWorkAstonProject.services.AppUserServiceImpl.*(..))")
    public void servicePoint() {
    }

    @AfterThrowing(value = "servicePoint()", throwing = "fle")
    public void afterThrowingWrongPassword(FailedLoginException fle) {
        logger.error("FAILED LOGIN: Wrong password.");
    }

    @AfterThrowing(value = "servicePoint()", throwing = "ise")
    public void wrongOldPassword(IllegalStateException ise) {
        logger.error("FAILED CHANGING PASSORD: Old password is incorrect.");
    }

    @AfterThrowing(value = "servicePoint()", throwing = "le")
    public void usernameAlreadyExists(LoginException le) {
        logger.error("FAILED REGISTRATION: This username already exists.");
    }


}
