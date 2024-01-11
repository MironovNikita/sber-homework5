import org.homework.beanUtils.BeanUtils;
import org.homework.trial.FromObject;
import org.homework.trial.ToObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BeanUtilsTest {
    @DisplayName("Проверка метода присвоения класса \"BeanUtils\"")
    @Test
    void shouldAssertTrueIfAllFieldsOfObjectsAreEqualAfterAssign() {
        FromObject fromObject = new FromObject("Имя", 28, LocalDate.of(1995, 8, 14));
        ToObject toObject = new ToObject();

        BeanUtils.assign(toObject, fromObject);

        Assertions.assertEquals(toObject.getName(), fromObject.getName());
        Assertions.assertEquals(toObject.getAge(), fromObject.getAge());
        Assertions.assertEquals(toObject.getBirthday(), fromObject.getBirthday());
    }

    @DisplayName("Проверка выброса исключения \"IllegalArgumentException\", если у объекта \"from\" есть null-поля")
    @Test
    void shouldThrowIllegalArgumentExceptionIfFromObjectContainsNullFields() {
        FromObject fromObject = new FromObject();
        ToObject toObject = new ToObject();

        Assertions.assertThrows(IllegalArgumentException.class, () -> BeanUtils.assign(toObject, fromObject));
    }

}
