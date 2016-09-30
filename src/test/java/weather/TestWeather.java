package weather;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import weather.json.CountryWeather;
import weather.utils.WeatherUtils;

public class TestWeather {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestWeather.class);
    private static final String API_KEY = "INSERT API KEY HERE";

    @Test
    public void testJsonParsing() {
        RestTemplate restTemplate = new RestTemplate();
        CountryWeather singapore = restTemplate
                .getForObject(WeatherUtils.buildWeatherUri("singapore", API_KEY), CountryWeather.class);
        Assert.assertEquals("SG", singapore.getSys().getCountry());
    }

    @Test
    public void testJsonArray() {
        RestTemplate restTemplate = new RestTemplate();
        CountryWeather singapore = restTemplate
                .getForObject(WeatherUtils.buildWeatherUri("singapore", API_KEY), CountryWeather.class);
        LOGGER.info(singapore.getWeather().get(0).getMain());
        Assert.assertNotNull(singapore.getWeather().get(0).getMain());
    }
}
