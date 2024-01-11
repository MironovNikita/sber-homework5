package org.homework.performanceProxy;

import org.homework.calculator.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PerformanceProxy implements InvocationHandler {
    private final Object target;

    public PerformanceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Metric.class)) return invoke(method, args);
        System.out.printf(
                "\nРасчёт времени выполнения метода \"%s\" с аргументом: %s%n", method.getName(), Arrays.toString(args)
        );
        long startTime = System.nanoTime();
        Object result = invoke(method, args);
        long endTime = System.nanoTime();
        System.out.printf("Время выполнения метода \"%s\" с аргументом: %s составило: %s наносекунд%n",
                method.getName(), Arrays.toString(args), endTime - startTime);
        return result;
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException exception) {
            throw new RuntimeException("Невозможно получить доступ!", exception);
        } catch (InvocationTargetException exception) {
            throw exception.getCause();
        }
    }
}
