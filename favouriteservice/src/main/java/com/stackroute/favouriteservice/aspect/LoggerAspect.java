package com.stackroute.favouriteservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.stackroute.favouriteservice.controller.FavouriteServiceController.*(..))")
	public void before(JoinPoint joinPoint) {
		logger.info("[ Executed method {} ]", joinPoint.toString());
	}

	@AfterReturning(pointcut = "addFavBook()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		logger.debug("Book added successfully ");
	}

	@AfterThrowing(pointcut = "addFavBook()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
		logger.error("Cause : " + exception.getCause());
	}

	@Pointcut("execution(* com.stackroute.favouriteservice.controller.FavouriteServiceController.addFavBook(..))")
	public void addFavBook() {

	}

}