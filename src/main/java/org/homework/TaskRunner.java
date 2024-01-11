package org.homework;

import org.homework.beanUtils.BeanUtils;
import org.homework.cachedProxy.CachedInvocationHandler;
import org.homework.calculator.Calculator;
import org.homework.calculator.CalculatorImpl;
import org.homework.performanceProxy.PerformanceProxy;
import org.homework.reflection.ClassReflectionHelper;
import org.homework.trial.*;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.time.LocalDate;

public class TaskRunner {
    public static void task1() {
        System.out.println("Выбрано: Простая проверка расчёта факториала числа");
        Calculator calculator = new CalculatorImpl();
        System.out.println(calculator.calc(0));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(8));
    }

    public static void task2() {
        System.out.printf(
                "Выбрано: Вывод всех методов класса (+ родительские и приватные для классов: %s и %s)",
                CalculatorImpl.class.getName(), Field.class.getName());
        ClassReflectionHelper.printAllClassMethods(CalculatorImpl.class);
        ClassReflectionHelper.printAllClassMethods(Field.class);
    }

    public static void task3() {
        System.out.printf("Выбрано: вывод всех геттеров класса (%s)", GetterTestClass.class.getName());
        ClassReflectionHelper.printAllClassGetters(GetterTestClass.class);
    }

    public static void task4() {
        System.out.println("Выбрано: проверка работы метода, сравнивающего имена констант с их значением");
        System.out.print("Случай №1 - должно быть true: ");
        try {
            System.out.println(ClassReflectionHelper.checkConstantsNamesEqualsValues(EqualConstants.class));
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.print("Случай №2 - должно быть false: ");
        try {
            System.out.println(
                    ClassReflectionHelper.checkConstantsNamesEqualsValues(NotEqualConstants.class)
            );
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void task5() {
        System.out.println("Выбрано: проверка кеширующего прокси");
        Calculator cachedCalc = new CalculatorImpl();
        Calculator proxiedCachedCalculator = (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                cachedCalc.getClass().getInterfaces(),
                new CachedInvocationHandler(cachedCalc)
        );

        System.out.println("\nПроверка кеширующего прокси:");
        checkProxy(proxiedCachedCalculator);
    }

    public static void task6() {
        System.out.println("Выбрано: проверка прокси, рассчитывающего время выполнения метода");
        Calculator performanceCalc = new CalculatorImpl();
        Calculator proxiedPerfCalculator = (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                performanceCalc.getClass().getInterfaces(),
                new PerformanceProxy(performanceCalc)
        );

        System.out.println("\nПроверка прокси производительности:");
        checkProxy(proxiedPerfCalculator);
    }

    public static void task7() {
        System.out.println("Выбрано: проверка реализации класса BeanUtils");
        FromObject fromObject = new FromObject(
                "Имя", 28, LocalDate.of(1995, 8, 14));
        ToObject toObject = new ToObject();

        System.out.printf("Поля fromObject до работы метода: %s, %d, %s%n",
                fromObject.getName(), fromObject.getAge(), fromObject.getBirthday());
        System.out.printf("Поля toObject до работы метода: %s, %d, %s%n",
                toObject.getName(), toObject.getAge(), toObject.getBirthday());

        try {
            BeanUtils.assign(toObject, fromObject);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.printf("\nПоля fromObject после работы метода: %s, %d, %s%n",
                fromObject.getName(), fromObject.getAge(), fromObject.getBirthday());
        System.out.printf("Поля toObject после работы метода: %s, %d, %s%n",
                toObject.getName(), toObject.getAge(), toObject.getBirthday());
    }

    private static void checkProxy(Calculator calculator) {
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(7));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
    }
}
