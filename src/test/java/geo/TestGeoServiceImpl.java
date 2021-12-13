package geo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.*;
import ru.netology.geo.*;


public class TestGeoServiceImpl {

    @Test
    public void check_byCoordinatesReturnsException() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Assertions.assertThrows(RuntimeException.class, () ->
                geoService.byCoordinates(0.1, 0.2));
    }

    @ParameterizedTest
    @ValueSource(strings = {GeoServiceImpl.LOCALHOST, GeoServiceImpl.MOSCOW_IP,
            GeoServiceImpl.NEW_YORK_IP, "172.0.38.71", "96.44.10.22"})
    public void check_byIpReturnsCorrectCountry(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Country countryExpected = null;
        switch (ip) {
            case GeoServiceImpl.LOCALHOST:
                break;
            case GeoServiceImpl.MOSCOW_IP:
            case "172.0.38.71":
                countryExpected = Country.RUSSIA;
                break;
            case GeoServiceImpl.NEW_YORK_IP:
            case "96.44.10.22":
                countryExpected = Country.USA;
                break;
        }
        Country result = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(countryExpected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {GeoServiceImpl.LOCALHOST, GeoServiceImpl.MOSCOW_IP,
            GeoServiceImpl.NEW_YORK_IP, "172.0.38.71", "96.44.10.22"})
    public void check_byIpReturnsCorrectCity(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        String cityExpected = null;
        switch (ip) {
            case GeoServiceImpl.LOCALHOST:
                break;
            case GeoServiceImpl.MOSCOW_IP:
            case "172.0.38.71":
                cityExpected = "Moscow";
                break;
            case GeoServiceImpl.NEW_YORK_IP:
            case "96.44.10.22":
                cityExpected = "New York";
                break;
        }
        String result = geoService.byIp(ip).getCity();
        Assertions.assertEquals(cityExpected, result);
    }

    @Test
    public void check_byIpIfNull() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        String ip = "34.11.31.09";
        Location result = geoService.byIp(ip);
        Assertions.assertNull(result);
    }
}
