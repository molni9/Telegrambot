package com.example.molni9boot.Config;

import com.example.molni9boot.service.Telegramboot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
@Slf4j
@Component
public class BotInitialaizer {
    @Autowired
    Telegramboot bot;
    @EventListener({ContextRefreshedEvent.class})
    public void  init() throws TelegramApiException{
        TelegramBotsApi telegrambootsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegrambootsApi.registerBot(bot);
        }
        catch (TelegramApiException e){
            log.error("Error occoord" + e.getMessage());

        }    }

}
