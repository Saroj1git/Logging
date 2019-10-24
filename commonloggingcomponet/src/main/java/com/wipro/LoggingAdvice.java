package com.wipro;
import brave.sampler.Sampler;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

	@Pointcut("execution(* com.wipro.*.*.*(..))")
	public void myPointcut() {

	}

	@Bean
	Sampler getSampler() {
        return Sampler.ALWAYS_SAMPLE;
	}




	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
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
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		Object obj=pjp.proceed();
		long endTime=System.currentTimeMillis();
		log.info("Method name"+pjp.getSignature()+" time taken to execute : "+(endTime-stratTime));
		Object object = pjp.proceed();

		return obj;
	}


	@AfterThrowing(pointcut = "execution(* com.wipro.*.*.*(..)))", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex){
		System.out.println("After Throwing exception in method:"+joinPoint.getSignature());
		System.out.println("Exception is:"+ex.getMessage());
	}

}
