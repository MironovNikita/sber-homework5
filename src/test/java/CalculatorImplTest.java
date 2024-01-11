import org.homework.calculator.Calculator;
import org.homework.calculator.CalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorImplTest {
    Calculator calculator = new CalculatorImpl();

    @DisplayName("Проверка вычисления факториала 0")
    @Test
    void shouldCountOneToZeroFactorial() {
        Assertions.assertEquals(1, calculator.calc(0));
    }

    @DisplayName("Проверка вычисления факториала 1")
    @Test
    void shouldCountOneToOneFactorial() {
        Assertions.assertEquals(1, calculator.calc(1));
    }

    @DisplayName("Проверка вычисления факториала 5")
    @Test
    void shouldCountOneHundredTwentyToFiveFactorial() {
        Assertions.assertEquals(120, calculator.calc(5));
    }

    @DisplayName("Проверка выброса исключения IllegalArgumentException")
    @Test
    void shouldThrowIllegalArgumentExceptionIfNumberIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           calculator.calc(-5);
        });
    }
}
