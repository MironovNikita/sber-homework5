package org.homework.calculator;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Вычисление факториала отрицательного числа невозможно!");
        }
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }

        return factorial;
    }
}
