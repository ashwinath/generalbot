package telegram;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import telegram.utils.TelegramUtils;

/**
 * Created by ashwin on 27/9/2016.
 */
public class WeatherBot extends TelegramLongPollingBot {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherBot.class);
    private static final String BOT_USERNAME = "WeatherBot";
    private static final String BOT_TOKEN = "***REMOVED***";

    public void onUpdateReceived(Update update) {
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

    public String getBotUsername() {
        return this.BOT_USERNAME;
    }

    public String getBotToken() {
        return this.BOT_TOKEN;
    }
}
