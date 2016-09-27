package weather;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import weather.json.CountryWeather;

/**
 * Created by ashwin on 27/9/2016.
 */
public class TestWeather {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestWeather.class);
    private static final String API_KEY = "API_KEY";

    @Test
    public void testJsonParsing() {
        RestTemplate restTemplate = new RestTemplate();
        CountryWeather singapore = restTemplate
                .getForObject("http://api.openweathermap.org/data/2.5/weather?apikey=" + this.API_KEY + "&q=singapore", CountryWeather.class);
        Assert.assertEquals("SG", singapore.getSys().getCountry());
    }
}
