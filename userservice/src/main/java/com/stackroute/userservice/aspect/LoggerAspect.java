package com.stackroute.userservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.stackroute.userservice.controller.UserServiceController.*(..))")
	public void before(JoinPoint joinPoint) {
		logger.info("[ Executed method {} ]", joinPoint.toString());
	}

	@AfterReturning(pointcut = "addUser()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		logger.debug("User added successfully ");
	}

	@AfterThrowing(pointcut = "addUser()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
		logger.error("Cause : " + exception.getCause());
	}

	@Pointcut("execution(* com.stackroute.userservice.controller.UserServiceController.addUser(..))")
	public void addUser() {

	}

}
