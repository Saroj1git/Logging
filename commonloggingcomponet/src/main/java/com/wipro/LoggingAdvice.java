package com.wipro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

	@Pointcut("execution(* com.*.*.*.*(..))")
	public void myPointcut() {

	}

	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();

		log.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
				+ mapper.writeValueAsString(array));
		Object object = pjp.proceed();
		log.info(className + " : " + methodName + "()" + "Response : "
				+ mapper.writeValueAsString(object));
		return object;
	}
	@Around("@annotation(com.wipro.advice.TrackExecutionTime)")
	public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
		long stratTime=System.currentTimeMillis();
		ObjectMapper mapper = new ObjectMapper();
		Object obj=pjp.proceed();
		long endTime=System.currentTimeMillis();
		log.info("Method name"+pjp.getSignature()+" time taken to execute : "+(endTime-stratTime));
		Object object = pjp.proceed();

		return obj;
	}


	@AfterThrowing(pointcut = "execution(* com.*.*.*.*(..)))", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex){
		System.out.println("After Throwing exception in method:"+joinPoint.getSignature());
		System.out.println("Exception is:"+ex.getMessage());
	}

}
