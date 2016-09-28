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

public class WeatherBot extends TelegramLongPollingBot {

    private final static Logger LOGGER = LoggerFactory.getLogger(WeatherBot.class);
    private String botUsername;
    private String botToken;
    private String openWeatherMapApiKey;

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            Message message = update.getMessage();

            if(message.hasText() && StringUtils.equals(message.getText(), "/weather")) {
                LOGGER.info("Message received");
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText(TelegramUtils.getWeatherString("singapore", openWeatherMapApiKey));
                try {
                    sendMessage(sendMessageRequest);
                } catch (TelegramApiException e) {
                    LOGGER.error(e.toString());
                }
            }
        }
    }

    public String getBotUsername() {
        return this.botUsername;
    }

    public void setBotUsername(String botUserName) {
        this.botUsername = botUserName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void setOpenWeatherMapApiKey(String openWeatherMapApi) {
        this.openWeatherMapApiKey = openWeatherMapApi;
    }

}
