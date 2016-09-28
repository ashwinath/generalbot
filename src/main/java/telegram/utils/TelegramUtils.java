package telegram.utils;

import org.springframework.web.client.RestTemplate;
import weather.json.CountryWeather;
import weather.utils.WeatherUtils;

public class TelegramUtils {
    public static String getWeatherString(String country, String apiKey) {
        RestTemplate restTemplate = new RestTemplate();
        CountryWeather countryWeather = restTemplate
                .getForObject(WeatherUtils.buildWeatherUri(country, apiKey), CountryWeather.class);
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
