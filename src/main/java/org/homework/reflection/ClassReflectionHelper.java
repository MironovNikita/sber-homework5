package org.homework.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassReflectionHelper {
    public static void printAllClassMethods(Class<?> clazz) {
        int i = 1;
        System.out.println("\nМетоды класса " + clazz.getName() + ":");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println((i++) + ". " + method.toString());
        }

        if (clazz.getSuperclass() != null) {
            printAllClassMethods(clazz.getSuperclass());
        }
    }

    public static void printAllClassGetters(Class<?> clazz) {
        int i = 1;
        System.out.println("\nГеттеры класса " + clazz.getName() + ":");
        List<Method> clazzGetterMethods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if ((method.getName().startsWith("get") || method.getName().startsWith("is")) &&
                    method.getParameters().length == 0 && (!void.class.equals(method.getReturnType()))) {
                clazzGetterMethods.add(method);
            }
        }

        if (clazzGetterMethods.isEmpty()) System.out.println("Класс не содержит геттеров!");
        else {
            for (Method method : clazzGetterMethods) System.out.println((i++) + ". " + method);
        }
    }

    public static boolean checkConstantsNamesEqualsValues(Class<?> clazz) throws IllegalAccessException {
        List<Field> clazzFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            if (field.getType().equals(String.class) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                clazzFields.add(field);
            }
        }

        if (clazzFields.isEmpty()) {
            return false;
        }

        for (Field field : clazzFields) {
            if (!field.getName().equals(field.get(clazz))) {
                return false;
            }
        }
        return true;
    }
}
