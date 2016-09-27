package telegram;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;
import telegram.utils.TelegramUtils;
import weather.json.CountryWeather;
import weather.utils.WeatherUtils;

/**
 * Created by ashwin on 27/9/2016.
 */
public class WeatherBot extends TelegramLongPollingCommandBot {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherBot.class);
    private static final String BOT_USERNAME = "WeatherBot";
    private static final String BOT_TOKEN = "***REMOVED***";

    public String getBotUsername() {
        return this.BOT_USERNAME;
    }

    public String getBotToken() {
        return this.BOT_TOKEN;
    }

    public void processNonCommandUpdate(Update update) {
        if(update.hasMessage()) {
            Message message = update.getMessage();

            if(message.hasText() && StringUtils.equals(message.getText(), "/weather")) {
                LOGGER.info("Message received");
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText(TelegramUtils.getWeatherString("singapore"));
                try {
                    sendMessage(sendMessageRequest);
                } catch (TelegramApiException e) {
                    LOGGER.error(e.toString());
                }
            }
        }
    }
}
