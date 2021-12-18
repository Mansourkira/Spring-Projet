package  configuration;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@Component
@Aspect

public class LoggingAspect {

	private static final Logger l =(Logger)  LogManager.getLogger(LoggingAspect.class);
	@Before("execution(* org.sid.service.StockService.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	l.info("In method " + name + " : ");
	}

	@After("execution(* org.sid.service.StockService.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		l.info("Out Of method " + name + " : ");
		}
}
