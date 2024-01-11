import org.homework.reflection.ClassReflectionHelper;
import org.homework.trial.EqualConstants;
import org.homework.trial.NotEqualConstants;
import org.junit.jupiter.api.*;

public class ClassReflectionHelperTest {
    @DisplayName("Все имена констант класса аналогичны их значению")
    @Test
    void shouldReturnTrueIfAllConstantNamesEqualValues() throws IllegalAccessException {
        Assertions.assertTrue(ClassReflectionHelper.checkConstantsNamesEqualsValues(EqualConstants.class));
    }

    @DisplayName("Не все имена констант класса аналогичны их значению")
    @Test
    void shouldReturnFalseIfNotAllConstantNamesEqualValues() throws IllegalAccessException {
        Assertions.assertFalse(ClassReflectionHelper.checkConstantsNamesEqualsValues(NotEqualConstants.class));
    }
}
