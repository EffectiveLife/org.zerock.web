package org.zerock.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by User on 2016-06-08.
 */
@Component
@Aspect
public class SampleAdvice {

    private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

    @Before("execution(* org.zerock.service.MessageService*.*(..))")
    public void startLog(JoinPoint jp) {
        logger.info("===============================");
        logger.info("===========LOG Start===========");
        logger.info("Joinpoint get arguments : " + Arrays.toString(jp.getArgs()));
        logger.info("Joinpoint get kinds : " + jp.getKind());
        logger.info("Joinpoint get signature : " + jp.getSignature().toString());
        logger.info("Joinpoint get target : " + jp.getTarget().toString());
        logger.info("Joinpoint get this : " + jp.getThis().toString());

    }

    @Around("execution(* org.zerock.service.MessageService*.*(..))")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        logger.info(Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed();

        long endTime = System.currentTimeMillis();
        logger.info(pjp.getSignature().getName() + " : " +(endTime-startTime));
        logger.info("===============================");

        return result;
    }

}
