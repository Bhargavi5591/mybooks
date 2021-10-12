package com.stackroute.userservice.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggerAOPEnabler {
	
	@Bean
	public LoggerAspect getLoggerAspect() {
		return new LoggerAspect();
	}

}