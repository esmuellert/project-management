package com.jrp.pma.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ApplicationLoggerAspect {
//    private final Logger log = LoggerFactory.getLogger((this.getClass()));
//
//    @Pointcut("within(com.jrp.pma.controllers..*)")
//    public void definePackagePointcuts() {
//        // empty method just to name the location specified in the pointcut
//    }
//
//    @Around("definePackagePointcuts()")
//    public Object logAround(ProceedingJoinPoint joinPoint) {
//        log.debug("\n \n \n");
//        log.debug("*************** Before Method Execution ***************** {}.{}() with arguments[s] = {}",
//                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
//                Arrays.toString(joinPoint.getArgs()));
//        log.debug("_______________________________________________________ \n \n \n");
//
//        Object object = null;
//        try {
//            object = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        log.debug("\n \n \n");
//        log.debug("*************** After Method Execution ***************** {}.{}() with arguments[s] = {}",
//                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
//                Arrays.toString(joinPoint.getArgs()));
//        log.debug("_______________________________________________________ \n \n \n");
//        return object;
//    }


}
