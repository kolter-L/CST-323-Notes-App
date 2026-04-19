package com.example.notesapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    /**
     * Intercepts all method calls within the notesapp package.
     * Logs method entry (DEBUG), exit (DEBUG), and exceptions (ERROR)
     * using the target class's own logger for accurate class-name attribution.
     */
    @Around("execution(* com.example.notesapp.*.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className  = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        Logger logger     = LogManager.getLogger(joinPoint.getTarget().getClass());

        logger.debug("ENTERING  {}.{}() - args: {}",
                className, methodName, Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();

            logger.debug("EXITING   {}.{}() - returned: {}",
                    className, methodName, result);

            return result;
        } catch (Exception e) {
            logger.error("EXCEPTION {}.{}() - {}: {}",
                    className, methodName,
                    e.getClass().getSimpleName(), e.getMessage());
            throw e;
        }
    }
}
