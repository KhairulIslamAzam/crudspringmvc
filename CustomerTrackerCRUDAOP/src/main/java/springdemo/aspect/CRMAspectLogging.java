package springdemo.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.persistence.OneToMany;
import java.util.logging.Logger;

@Aspect
@Component
public class CRMAspectLogging {

    private Logger logger = Logger.getLogger(getClass().getName());

    //poincut
    @Pointcut("execution(* springdemo.controller.*.*(..))")
    public void forControllerPackage(){

    }

    //poincut
    @Pointcut("execution(* springdemo.service.*.*(..))")
    public void forServicePackage(){

    }

    //poincut
    @Pointcut("execution(* springdemo.dao.*.*(..))")
    public void forDaoPackage(){

    }

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    public void forCombinePackage(){

    }

    @Before("forCombinePackage()")
    public void before(JoinPoint theJointPoint){

        //display the method name
        String theMethod = theJointPoint.getSignature().toLongString();
        logger.info("@Before Calling the method "+theMethod);

        Object [] args = theJointPoint.getArgs();


        for (Object tempArgs: args){
            logger.info("arguemnts: "+tempArgs);
        }
    }

    @AfterReturning(
            pointcut = "forCombinePackage()",
            returning = "theResult")
    public void after(JoinPoint theJointPoint, Object theResult){

        //display the method name
        String theMethod = theJointPoint.getSignature().toLongString();
        logger.info("@After Calling the method "+theMethod);

        logger.info("After Result "+theResult);
    }



}
