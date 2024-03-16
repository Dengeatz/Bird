package com.example.demo.aspects;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution(public * loginToAccount(*))")
    public void logMethod() {}

    @Before("logMethod()")
    public void logTry() {
        System.out.println("Проводим логирование файла");
    }
}
