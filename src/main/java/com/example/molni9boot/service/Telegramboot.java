package com.example.molni9boot.service;

import com.example.molni9boot.Config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class Telegramboot extends TelegramLongPollingBot {
    final BotConfig config;

    static final String help_Text = "В общем в этом боту вы можете бранировать себе место для стрижки в этом боту есть команды такие как:\n\n"
            + "Еще в разроботке";


    public Telegramboot(BotConfig config){
        this.config = config;
        List<BotCommand> listofCommands = new ArrayList();
        listofCommands.add(new BotCommand("/start","get a welcom massage"));
        listofCommands.add(new BotCommand("/help","Помощь тебе"));
        listofCommands.add(new BotCommand("mydata","get your data store"));
        listofCommands.add(new BotCommand("/deletedata","Убрать тебя нахуй твои данные"));
        listofCommands.add(new BotCommand("/settings","Поменять настройки"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

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
                case "/help":
                    sendMassage(chatId, help_Text);
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
