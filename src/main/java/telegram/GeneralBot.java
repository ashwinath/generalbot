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
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(message.getChatId().toString());
            String messageText = message.getText();
            String[] command = StringUtils.split(messageText);
            if (command.length == 0) {
                return;
            }

            switch (command[0]) {
                case ("/weather"): {
                    sendWeatherDetails(command, sendMessageRequest);
                    break;
                }
            }
        }
    }
    private void sendWeatherDetails(String[] command, SendMessage sendMessageRequest) {
        if (command.length == 1) {
            LOGGER.info("[sendWeatherDetails] Message Received. No country specified");
            sendMessageRequest.setText("Please specify a country.");
        } else {
            try {
                String country = command[1];
                LOGGER.info("Message received. Country: " + country);
                String weatherString = TelegramUtils.getWeatherString(country, openWeatherMapApiKey);
                LOGGER.info("Return message: " + weatherString);
                sendMessageRequest.setText(weatherString);
            } catch (IllegalArgumentException e) {
                sendMessageRequest.setText("No such country.");
                LOGGER.warn("No such country.");
            }

            sendMessageWithLogging(sendMessageRequest);
        }

    }

    public void sendMessageWithLogging(SendMessage sendMessageRequest) {
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            LOGGER.error(e.toString());
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
