package telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

public class MainTelegram {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherBot.class);

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(context.getBean("weatherBot", WeatherBot.class));
        } catch (TelegramApiException e) {
            LOGGER.error(e.toString());
        }
    }
}
