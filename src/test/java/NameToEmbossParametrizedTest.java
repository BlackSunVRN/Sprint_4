import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class NameToEmbossParametrizedTest {
    public final String name;
    public final boolean expected;

    public NameToEmbossParametrizedTest(String name, boolean expected) {
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}")
    public static Object[][] getNamesDataForTest() {
        return new Object[][]{
                {"Xx", false}, // длина имени на 1 символ меньше минимально допустимого значения [2]
                {"X X", true}, // длина имени равна граничному значению 3 [3]
                {"X Xx", true}, // длина имени на 1 символ больше минимально допустимого значения [4]
                {"Xxxxx Xxxxxxx", true}, // длина имени в диапазоне 3-19 [13]
                {"Xxxxx Xxxxxxxxxxxx", true}, // длина имени на 1 символ меньше максимально допустимого значения [18]
                {"Xxxxx Xxxxxxxxxxxxx", true}, // длина имени равна граничному значению 19 [19]
                {"Xxxxx Xxxxxxxxxxxxxx", false}, // длина имени на 1 символ больше максимально допустимого значения [20]
                {"XxxxxXxxxx", false}, // имя не содержит пробелов
                {" Xxxxx Xxxxxx", false}, // имя содержит пробел в начале строки
                {"Xxxxx  Xxxxx", false}, // имя содержит 2 пробела подряд
                {"Xxxxx Xxxxx ", false}, // имя содержит пробел в конце строки
                {"", false}, // имя передано пустой строкой
                {null, false} // null в имени
        };
    }

    @Test
    @DisplayName("Check names to be Embossed")
    @Description("Check names from parametrized data method")
    public void shouldBeEmbossedName() {
        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        Assert.assertEquals(expected, actual);
    }
}
