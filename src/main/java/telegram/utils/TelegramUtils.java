package telegram.utils;

import org.springframework.web.client.RestTemplate;
import weather.json.CountryWeather;
import weather.utils.WeatherUtils;

/**
 * Created by ashwin on 27/9/2016.
 */
public class TelegramUtils {
    public static String getWeatherString(String country) {
        RestTemplate restTemplate = new RestTemplate();
        CountryWeather countryWeather = restTemplate
                .getForObject(WeatherUtils.buildWeatherUri(country), CountryWeather.class);
        StringBuilder sb = new StringBuilder();
        sb.append("The temperature today is: Hi: ")
                .append(String.format("%.1f", countryWeather.getMain().getMaxTemp()))
                .append("°C ")
                .append("Lo: ")
                .append(String.format("%.1f", countryWeather.getMain().getMinTemp()))
                .append("°C");
        return sb.toString();
    }
}
