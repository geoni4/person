package com.cos.person.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cos.person.domain.CommonDto;


//@Controller, @RestController, @Component, @Configuration
@Component
@Aspect
public class BindingAdvice {

  private final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

  @Before("execution(* com.cos.person.web..*Controller.*(..))")
  public void testCheck( //ProceedingJoinPoint proceedingJoinPoint
       ){
    // request 처리
    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    System.out.println("주소: " +req.getRequestURI());

    
    // String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
    // String method = proceedingJoinPoint.getSignature().getName();
    // System.out.println("type: "+ type + "method: " + method);

  }


  //@Before, @After, @Around
  @Around("execution(* com.cos.person.web..*Controller.*(..))")
  public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
    String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
    String method = proceedingJoinPoint.getSignature().getName();

    Object[] args = proceedingJoinPoint.getArgs();

    for(Object arg : args){
      if(arg instanceof BindingResult){
        BindingResult bindingResult = (BindingResult) arg;

        // 서비스: 정상적인 화면 -> 사용자 요청 시 정상적으로 동작할 것을 예상
        //
        if(bindingResult.hasErrors()){
          Map<String, Object> errorMap = new HashMap<>();
          
          for(FieldError error: bindingResult.getFieldErrors()){
            errorMap.put(error.getField(), error.getDefaultMessage());
            log.warn(type + "." + method +"() => field: " + error.getField() + ", message: "+error.getDefaultMessage());
          }
          return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
        }

      }
    }

    return proceedingJoinPoint.proceed();

  }
  
}
