package com.company.Utils;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

public class MessageUtil {
    static HashMap<Integer, String> digits = new HashMap<>();
    static {
        digits.put(0, "0️⃣");
        digits.put(1, "1️⃣");
        digits.put(2, "2️⃣");
        digits.put(3, "3️⃣");
        digits.put(4, "4️⃣");
        digits.put(5, "5️⃣");
        digits.put(6, "6️⃣");
        digits.put(7, "7️⃣");
        digits.put(8, "8️⃣");
        digits.put(9, "9️⃣");
        digits.put(10, "🔟");
    }

    public static List<InlineKeyboardButton> buttonRow(String text, String callBackData){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callBackData);
        List<InlineKeyboardButton> list = new LinkedList<>();
        list.add(button);
        return list;
    }

    public static InlineKeyboardMarkup getMarkup(List<InlineKeyboardButton>... args){
        List<List<InlineKeyboardButton>> rowList = Arrays.stream(args).toList();

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(rowList);
        return markup;
    }

    public static InlineKeyboardMarkup getButtons(ArrayList<String> names, Integer offset){
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        List<InlineKeyboardButton> row;

        for (int i = 0; i < names.size(); i++){
            row = buttonRow(digits.get(i + 1) + " " + names.get(i), String.valueOf(i));
            rowList.add(row);
        }
        if (offset == 0) {
            row = buttonRow("➡️ next", "next");
            rowList.add(row);
        } else {
            row = buttonRow("⬅️ back", "back");
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("➡️ next");
            button.setCallbackData("next");
            row.add(button);
            rowList.add(row);
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(rowList);
        return markup;
    }

    public static SendMessage startMessage(Long userId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userId.toString());
        String text = "Assalomu aleykum, SearchingBot ga xush kelibsiz. Bu" +
                " bot sizga barcha 🎵qo'shiqlarni 🔎qidirshga yordam beradi.";
        sendMessage.setText(text);
        return sendMessage;
    }

}
