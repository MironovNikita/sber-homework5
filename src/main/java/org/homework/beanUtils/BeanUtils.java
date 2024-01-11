package org.homework.beanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Сканирует объект "from" на все getter'ы. Если объект "to"
 * содержит соответствующий setter, он вызовет его, чтобы установить значение свойства для "to",
 * которое равно свойству "from".
 * Тип в setter должен быть совместим со значением, возвращаемым getter (если нет, вызов не выполняется).
 * Совместимость означает, что тип параметра в setter должен совпадать или быть суперклассом возвращаемого типа getter.
 * <p>
 * Метод обрабатывает только общедоступные методы.
 *
 * @param "to"   - объект, свойства которого будут установлены.
 * @param "from" - объект, свойства которого будут использоваться для получения значений.
 */
public class BeanUtils {
    public static void assign(Object to, Object from) {
        if (hasNullFields(from)) throw new IllegalArgumentException("Исходный объект содержит null-поля");

        Class<?> toClass = to.getClass();
        Class<?> fromClass = from.getClass();

        List<Method> fromGetters = Arrays.stream(fromClass.getMethods()).filter(method ->
                (method.getName().startsWith("get") || method.getName().startsWith("is")) &&
                        method.getParameterTypes().length == 0 && (!void.class.equals(method.getReturnType()))).toList();

        List<Method> toSetters = Arrays.stream(toClass.getMethods()).filter(method ->
                method.getName().startsWith("set") && method.getParameters().length == 1).toList();

        for (Method getter : fromGetters) {
            String fieldName = getter.getName().substring(3);

            for (Method setter : toSetters) {
                if (setter.getName().substring(3).equals(fieldName)) {
                    try {
                        setter.invoke(to, getter.invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
            }
        }
    }

    private static boolean hasNullFields(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(object) == null) return true;
            } catch (IllegalAccessException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return false;
    }
}
