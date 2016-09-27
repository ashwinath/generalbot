package weather.utils;

import org.apache.http.client.utils.URIBuilder;

public class WeatherUtils {
    private static final String API_KEY = "API_KEY";

    public static String buildWeatherUri(String country) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/weather")
                .setParameter("apikey", API_KEY)
                .setParameter("q", country);
        return uriBuilder.toString();
    }
}
