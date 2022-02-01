package com.company;

import com.company.Dto.Hit;
import com.company.Dto.PostDto;
import com.company.Dto.Track;
import com.company.Utils.MessageUtil;
import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class Manager {

    static Integer offset = 0;
    static String text;

    public static PostDto getDto(String txt, Integer offset){
        text = txt;
        String name = txt.replace(" ", "%20");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://shazam.p.rapidapi.com/search?term=" + name + "&locale=en-US&offset=" + offset + "&limit=5"))
                .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                .header("x-rapidapi-key", "853a2ee22amsh97093faee10b370p1a3687jsn5cc27505d7cc")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        Gson gson = new Gson();
        PostDto dto = gson.fromJson(response.body(), PostDto.class);

        System.out.println(dto.getTracks().getHits()[0].getTrack().getUrl());
        return dto;
    }
    public static PostDto reGetDto(){
        offset++;
        return getDto(text, offset);
    }

    public static File downloadFile(String urlStr, String fileName) {
        String extension = urlStr.substring(urlStr.lastIndexOf("."));
        String path = "resources/" + fileName + extension;
        File file = new File(path);
        if (file.exists())
            file.delete();

        try {
            URL url = new URL(urlStr);
            try (InputStream in = url.openStream();
                ReadableByteChannel rbc = Channels.newChannel(in);
                FileOutputStream fos = new FileOutputStream(file)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static SendMessage getMusicList(PostDto dto, Long userId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userId.toString());
        sendMessage.setParseMode("Html");
        String text = "";

        if (dto == null || dto.getTracks() == null || dto.getTracks().getHits().length == 0) {
            text += "❓ Such music was not found.";
        } else {
            Hit[] hitList = dto.getTracks().getHits();
            text += hitList.length + " musics were found ❗";
            ArrayList<String> names = new ArrayList<>();
            Track track = null;

            for (Hit hit : hitList) {
                track = hit.getTrack();
                names.add(track.getTitle() + " - " + track.getSubtitle());
            }
            InlineKeyboardMarkup inlineKeyboardMarkup = MessageUtil.getButtons(names, offset);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        sendMessage.setText(text);
        return sendMessage;
    }
}
