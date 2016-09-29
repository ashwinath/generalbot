package telegram.utils;

import org.springframework.web.client.RestTemplate;
import weather.json.CountryWeather;
import weather.utils.WeatherUtils;

public class TelegramUtils {
    private static final String DEGREE_SYMBOL = "\u00b0";
    public static String getWeatherString(String country, String apiKey) throws IllegalArgumentException {
        RestTemplate restTemplate = new RestTemplate();
        CountryWeather countryWeather = restTemplate
                .getForObject(WeatherUtils.buildWeatherUri(country, apiKey), CountryWeather.class);
        if (countryWeather.getSys() == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The temperature today in " + countryWeather.getName() + ", " + countryWeather.getSys().getCountry() + " is: Hi: ")
                .append(String.format("%.1f", countryWeather.getMain().getMaxTemp()))
                .append(DEGREE_SYMBOL)
                .append("C ")
                .append("Lo: ")
                .append(String.format("%.1f", countryWeather.getMain().getMinTemp()))
                .append(DEGREE_SYMBOL)
                .append("C")
                .append(", ")
                .append(checkWeatherConditionCodeAndFormatString(countryWeather.getWeather().get(0).getId()))
                .append(".");
        return sb.toString();
    }

    private static String checkWeatherConditionCodeAndFormatString(long conditionId) {
        String weatherConditionString = null;
        int conditionIdInt = (int) conditionId;
        switch (conditionIdInt) {
            case 200: weatherConditionString = "and there is a thunderstorm with light rain"; break;
            case 201: weatherConditionString = "and there is a thunderstorm with rain"; break;
            case 202: weatherConditionString = "and there is a thunderstorm with heavy rain"; break;
            case 210: weatherConditionString = "and there is a light thunderstorm"; break;
            case 211: weatherConditionString = "and there is a thunderstorm"; break;
            case 212: weatherConditionString = "and there is a heavy thunderstorm"; break;
            case 221: weatherConditionString = "and there is a ragged thunderstorm"; break;
            case 230: weatherConditionString = "and there is a thunderstorm with light drizzle"; break;
            case 231: weatherConditionString = "and there is a thunderstorm with drizzle"; break;
            case 232: weatherConditionString = "and there is a thunderstorm with heavy drizzle"; break;

            case 300: weatherConditionString = "and it is drizzling lightly"; break;
            case 301: weatherConditionString = "and it is drizzling"; break;
            case 302: weatherConditionString = "and it is drizzling heavily"; break;
            case 310: weatherConditionString = "and it is drizzling lightly"; break;
            case 311: weatherConditionString = "and it is drizzling"; break;
            case 312: weatherConditionString = "and it is drizzling heavily"; break;
            case 313: weatherConditionString = "and there is a shower rain and drizzle"; break;
            case 314: weatherConditionString = "and there is heavy shower rain and drizzle"; break;
            case 321: weatherConditionString = "and there is a shower drizzle"; break;

            case 500: weatherConditionString = "and it is raining lightly"; break;
            case 501: weatherConditionString = "and it is raining moderately"; break;
            case 502: weatherConditionString = "and it is raining heavily"; break;
            case 503: weatherConditionString = "and it is raining very heavily"; break;
            case 504: weatherConditionString = "and it is raining extremely heavily"; break;
            case 511: weatherConditionString = "and there is freezing rain"; break;
            case 520: weatherConditionString = "and it is raining lightly"; break;
            case 521: weatherConditionString = "and it is raining"; break;
            case 522: weatherConditionString = "and it is raining heavily"; break;
            case 531: weatherConditionString = "and there is a ragged shower rain"; break;

            case 600: weatherConditionString = "and it is snowing lightly"; break;
            case 601: weatherConditionString = "and it is snowing"; break;
            case 602: weatherConditionString = "and it is snowing heavily"; break;
            case 611: weatherConditionString = "and there is sleet"; break;
            case 612: weatherConditionString = "and there is shower sleet"; break;
            case 615: weatherConditionString = "and it is snowing and raining lightly"; break;
            case 616: weatherConditionString = "and it is snowing and raining"; break;
            case 620: weatherConditionString = "and there is a light snow shower"; break;
            case 621: weatherConditionString = "and there is a snow shower"; break;
            case 622: weatherConditionString = "and there is a heavy snow shower"; break;

            case 701: weatherConditionString = "and it is misty"; break;
            case 711: weatherConditionString = "and it is smoky"; break;
            case 721: weatherConditionString = "and it is hazy"; break;
            case 731: weatherConditionString = "and there is sand and dust whirls"; break;
            case 741: weatherConditionString = "and it is foggy"; break;
            case 751: weatherConditionString = "and it is sandy"; break;
            case 761: weatherConditionString = "and it is dusty"; break;
            case 762: weatherConditionString = "and there is volcanic ash everywhere"; break;
            case 771: weatherConditionString = "and there are squalls"; break;
            case 781: weatherConditionString = "and there is a tornado"; break;

            case 800: weatherConditionString = "and the sky is clear"; break;
            case 801: weatherConditionString = "and there are a few clouds"; break;
            case 802: weatherConditionString = "and there are scattered clouds"; break;
            case 803: weatherConditionString = "and there are broken clouds"; break;
            case 804: weatherConditionString = "and there are overcast clouds"; break;

            case 900: weatherConditionString = "and there is a tornado"; break;
            case 901: weatherConditionString = "and there is a tropical storm"; break;
            case 902: weatherConditionString = "and there is a hurricane"; break;
            case 903: weatherConditionString = "and it is cold"; break;
            case 904: weatherConditionString = "and it is hot"; break;
            case 905: weatherConditionString = "and it is windy"; break;
            case 906: weatherConditionString = "and there is hail"; break;

            case 951: weatherConditionString = "and it is calm"; break;
            case 952: weatherConditionString = "and there is a light breeze"; break;
            case 953: weatherConditionString = "and there is a gentle breeze"; break;
            case 954: weatherConditionString = "and there is a moderate breeze"; break;
            case 955: weatherConditionString = "and there is a fresh breeze"; break;
            case 956: weatherConditionString = "and there is a strong breeze"; break;
            case 957: weatherConditionString = "and there is a high wind, near gale"; break;
            case 958: weatherConditionString = "and there is a gale"; break;
            case 959: weatherConditionString = "and there is a severe gale"; break;
            case 960: weatherConditionString = "and there is a storm"; break;
            case 961: weatherConditionString = "and there is a violent storm"; break;
            case 962: weatherConditionString = "and there is a hurricane"; break;

        }
        return weatherConditionString;
    }
}
