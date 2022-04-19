package org.prgms.kdt.kdtspringorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// target
class CalculatorImpl implements Calculator{

    @Override
    public int add(int a, int b) {
        return  a + b;
    }
}

// interface
interface Calculator{
    int add(int a, int b);

}

//invocation handler
class LoggingInvocationHandler implements InvocationHandler{

    private static final Logger log = LoggerFactory.getLogger(LoggingInvocationHandler.class);
    private final Object target;

    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("{} executed in {}", method.getName(), target.getClass().getName());
        return method.invoke(target, args);
    }
}

public class JdkProxyTest {

    private static final Logger log = LoggerFactory.getLogger(JdkProxyTest.class);

    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();
        calculator = (Calculator) Proxy.newProxyInstance(LoggingInvocationHandler.class.getClassLoader(),
                new Class[]{Calculator.class},
                new LoggingInvocationHandler(new CalculatorImpl()));
        int result = calculator.add(1,2);
        log.info("ADD -> {}", result);
    }

}
