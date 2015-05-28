package com.gtfs.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Logging {
	private Logger logger= Logger.getLogger(Logging.class);
	
	@Before("execution(* com.gtfs.controller.*.*(..))")
	public void printLog(JoinPoint joinPoint){
		logger.info("Method Execute "+joinPoint.getSignature());
		System.out.println("Method Execute "+joinPoint.getSignature());
	}
}
