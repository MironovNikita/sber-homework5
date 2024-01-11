package org.homework.cachedProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {
    private final Map<Object, Object> resultByArg = new HashMap<>();
    private final Object target;

    public CachedInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!method.isAnnotationPresent(Cache.class)) return invoke(method, args);
        if(!resultByArg.containsKey(key(method, args))) {
            System.out.printf("Запись в кеш метода: \"%s\" с аргументом: %s%n", method.getName(), Arrays.toString(args));
            Object invoke = invoke(method, args);
            resultByArg.put(key(method, args), invoke);
        }
        return resultByArg.get(key(method, args));
    }

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException exception) {
            throw new RuntimeException("Невозможно  получить доступ!", exception);
        } catch (InvocationTargetException exception) {
            throw exception.getCause();
        }
    }
}
