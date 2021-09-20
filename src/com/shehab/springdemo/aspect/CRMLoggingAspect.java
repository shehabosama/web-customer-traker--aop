package com.shehab.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pontcut declarations 
	@Pointcut("execution(* com.shehab.springdemo.controller.*.*(..))")
	private void forControllerPackage() { 
		
	}
	// do the same thing for service and dao
	@Pointcut("execution(* com.shehab.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.shehab.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage() ||forDaoPackage() || forServicePackage()")
	private void forAppFlow() {}
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		//display method we are calling 
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===========> in @Before : calling method : "+ method);
		// display the arguments to the method 
		// get the arguments
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object object : args) {
			myLogger.info("======> arguments: "+ object );
		}
		
	}
	//add @afterReturning advice

	@AfterReturning(pointcut = "forAppFlow()",returning = "theResult")
	public void afterReturning(JoinPoint thJoinPoint, Object theResult) {
		//display method we are returning from 
		String method = thJoinPoint.getSignature().toShortString();
		myLogger.info("==================> in @AfterReturning : calling method : "+ method);
		
		//display data returned
		myLogger.info("==================> result : "+ theResult);
	}
}




















