package weather.utils;

import org.apache.http.client.utils.URIBuilder;

public class WeatherUtils {
    public static String buildWeatherUri(String country, String apiKey) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/weather")
                .setParameter("apikey", apiKey)
                .setParameter("q", country);
        return uriBuilder.toString();
    }
}
