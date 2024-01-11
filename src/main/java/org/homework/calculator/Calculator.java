package org.homework.calculator;

import org.homework.cachedProxy.Cache;
import org.homework.performanceProxy.Metric;

public interface Calculator {
    /**
     * Расчёт факториала числа.
     * @param number передаваемое в метод неотрицательное число
     */
    @Cache
    @Metric
    int calc (int number);
}
