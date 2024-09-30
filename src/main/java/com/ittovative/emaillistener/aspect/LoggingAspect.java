package com.ittovative.emaillistener.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.ittovative.emaillistener.constant.AOPConstant.*;
import static com.ittovative.emaillistener.util.AspectUtil.*;


@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Around advice for logging
     * before and after executing project methods.
     *
     * @param joinPoint which contains details about method called
     * @return the return value of the method
     */
    @Around("execution(* com.ittovative.emaillistener..*.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = getClassName(joinPoint);
        String methodName = getMethodName(joinPoint);
        StringBuilder args = getMethodArgs(joinPoint);
        Object returnVal = null;

        LOGGER.info(EXECUTING_METHOD, className, methodName, args);
        try {
            returnVal = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            LOGGER.error(EXECUTING_EXCEPTION, throwable, className, methodName, args);
            throw throwable;
        }
        LOGGER.info(EXECUTING_FINISHED, className, methodName, args, returnVal);

        return returnVal;
    }
}