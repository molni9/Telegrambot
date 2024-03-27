package com.example.molni9boot.service;

import com.example.molni9boot.Config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Telegramboot extends TelegramLongPollingBot {
    final BotConfig config;
    public Telegramboot(BotConfig config){
        this.config = config;
    }
    @Override
    public String getBotUsername() {
        return config.getBootName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String massageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (massageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName() );
                    break;
                default: sendMassage(chatId,"ПОШЕЛ НАХУЙ");

            }
        }

    }

    private void startCommandReceived(Long chartId, String name) {
        String answer = "HI" + " " + name + " " + "Ебвл твою маму";
        sendMassage(chartId, answer);
    }
    private void  sendMassage(Long chartId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chartId));
        message.setText(textToSend);
        try {
            execute(message);
        }
        catch (TelegramApiException e){
        }
    }

}
