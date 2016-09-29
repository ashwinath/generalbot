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

public class GeneralBot extends TelegramLongPollingBot {

    private final static Logger LOGGER = LoggerFactory.getLogger(GeneralBot.class);
    private String botUsername;
    private String botToken;
    private String openWeatherMapApiKey;

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            Message message = update.getMessage();

            String messageText = message.getText();
            String command = StringUtils.substring(messageText, 0, 8);
            if(message.hasText() && StringUtils.equals(command, "/weather")) {
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                String country = StringUtils.substring(messageText, 9);

                if (StringUtils.trim(country).length() == 0) {
                    LOGGER.info("Message Received. No country specified");
                    sendMessageRequest.setText("Please specify a country.");
                } else {
                    try {
                        LOGGER.info("Message received. Country: " + country);
                        String weatherString = TelegramUtils.getWeatherString(country, openWeatherMapApiKey);
                        LOGGER.info("Return message: " + weatherString);
                        sendMessageRequest.setText(weatherString);
                    } catch (IllegalArgumentException e) {
                        sendMessageRequest.setText("No such country.");
                        LOGGER.warn("No such country.");
                    }
                }

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
