package org.prgms.kdt.kdtspringorder.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // 이제 이 메서드를 해당 영역에서 사용할 수 있다
    @Pointcut("execution(public * org.prgms.kdt.kdtspringorder..*Service.*(..))")
    public void servicePublicMethodPointcut(){};

    // "@annotation(org.prgms.kdt.kdtspringorder.aop.TrackTime)" : 특정 어노테이션이 부여된 메서드에만 쓰겠다.
    @Around("org.prgms.kdt.kdtspringorder.aop.CommonPointcut.repositoryMethodPointcut()") // 포인트 컷을 줘야한다.
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        var startTime = System.nanoTime(); // 1 -> 1,000,000,000
        log.info("Before method called. {}", joinPoint.getSignature());
        var result = joinPoint.proceed();
        var endTime = System.nanoTime() - startTime;
        log.info("After method called with result => {}, and time taken by {} nanoseconds", result, endTime);
        return result;
    }


}
