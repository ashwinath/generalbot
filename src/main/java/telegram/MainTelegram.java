package telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by ashwin on 27/9/2016.
 */
public class MainTelegram {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherBot.class);
    public static void main(String... args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new WeatherBot());
        } catch (TelegramApiException e) {
            LOGGER.error(e.toString());
        }
    }
}
