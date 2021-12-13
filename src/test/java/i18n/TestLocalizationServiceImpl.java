package i18n;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.*;

public class TestLocalizationServiceImpl {
    @ParameterizedTest
    @EnumSource(Country.class)
    public void check_byIpReturnsCorrectCountry(Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String stringExpected = null;
        switch (country) {
            case RUSSIA:
                stringExpected = "Добро пожаловать";
                break;
            default:
                stringExpected = "Welcome";
                break;
        }
        String result = localizationService.locale(country);
        Assertions.assertEquals(stringExpected, result);
    }
}
