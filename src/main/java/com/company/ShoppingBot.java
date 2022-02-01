package com.company;


import com.company.Dto.PostDto;
import com.company.Dto.Track;
import com.company.Utils.MessageUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;

public class ShoppingBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "@searchingjavabot";
    }

    @Override
    public String getBotToken() {
        return "5012734396:AAEViayENwwK_14gzPydm3E8wg7I8_BjwRc";
    }

    PostDto dto = null;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();

            if (message.hasText()){
                String text = message.getText();
                System.out.println(text);
                SendMessage sendMessage;

                switch (text) {
                    case "/start":
                        sendMessage = MessageUtil.startMessage(message.getChatId());
                        send(sendMessage);
                        return;

                    default:
                        Manager.offset = 0;
                        dto = Manager.getDto(text, 0);
                        sendMessage = Manager.getMusicList(dto, message.getChatId());
                        send(sendMessage);
                        return;
                }
            }
        }
        if (update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            System.out.println(data);

            switch (data) {
                case "next":
                    Manager.offset++;
                    dto = Manager.reGetDto();
                    SendMessage sendMessage = Manager.getMusicList(dto, callbackQuery.getFrom().getId());
                    send(sendMessage);
                    return;

                case "back":
                    Manager.offset--;
                    dto = Manager.reGetDto();
                    sendMessage = Manager.getMusicList(dto, callbackQuery.getFrom().getId());
                    send(sendMessage);
                    return;

                default:
                    int index = Integer.parseInt(data);
                    Track track = dto.getTracks().getHits()[index].getTrack();
                    String urlStr = track.getHub().getActions()[1].getUri();
                    String name = track.getTitle();
                    System.out.println(urlStr);
                    File file = Manager.downloadFile(urlStr, name);
                    sendFile(file, callbackQuery.getFrom().getId());
                    break;
            }
        }
    }

    public void  send(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void  sendFile(File file, Long userId){
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(userId.toString());
        sendDocument.setDocument(new InputFile(file));
        sendDocument.setCaption("By SearchingBot");
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
