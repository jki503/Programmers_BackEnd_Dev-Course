package org.prgms.kdt.kdtspringorder.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

    @Pointcut("execution(public * org.prgms.kdt.kdtspringorder..*Service.*(..))")
    public void servicePublicMethodPointcut(){};

    @Pointcut("execution(* org.prgms.kdt.kdtspringorder..*Repository.*(..))")
    public void repositoryMethodPointcut(){};
}
