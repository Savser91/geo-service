package sender;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import ru.netology.entity.*;
import ru.netology.geo.*;
import ru.netology.i18n.*;
import ru.netology.sender.*;
import java.util.HashMap;
import java.util.Map;

public class TestMessageSenderImpl {

    @Test
    public void check_SenderSendsCorrectMessageIfRus() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        String ip = "172.0.32.11";

        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String stringExpected = "Добро пожаловать";
        String result = messageSender.send(headers);
        Assertions.assertEquals(stringExpected, result);
    }

    @Test
    public void check_SenderSendsCorrectMessageIfNotRus() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        String ip = "96.44.10.22";

        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String stringExpected = "Welcome";
        String result = messageSender.send(headers);
        Assertions.assertEquals(stringExpected, result);
    }
}