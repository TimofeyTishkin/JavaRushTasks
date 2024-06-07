package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    SomeInterfaceWithMethods onlyThis;
    public CustomInvocationHandler(SomeInterfaceWithMethods onlyThis) {
        this.onlyThis = onlyThis;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.format("[%s in%n]", method.getName());
        Object result = method.invoke(onlyThis, args);
        System.out.format("[%s out%n]", method.getName());
        return result;
    }
}
