package com.nails.nastya.nailsme.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class TimeUtil {

    public static final Set<String> CALENDAR_CONTROL = Set.of(TelegramHelper.IGNORE);

    //TODO Ограничение, что в прошлом нельзя записаться,
    // и отключить кнопку "назад" в начальном календаре - завязаться на текущую дату
    // При недоступных данных, не выводить даты и услуги
    public List<List<InlineKeyboardButton>> generateTimeKeyboard(LocalDate date,
                                                                 Set<LocalTime> workTime,
                                                                 Set<LocalTime> blockTime,
                                                                 String callbackPrefix) {

        if (date == null) {
            return Collections.emptyList();
        }

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // row - Month and Year
        List<InlineKeyboardButton> headerRow = new ArrayList<>();
        headerRow.add(TelegramHelper.createControlButton(callbackPrefix, TelegramHelper.IGNORE,
                date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), null));
        keyboard.add(headerRow);

        int maxCountColumn = 6;
        List<LocalTime> sortedTime = workTime.stream().sorted().toList();
        int rows = (sortedTime.size() % maxCountColumn > 0 ? 1 : 0) + sortedTime.size() / maxCountColumn;

        for (int i = 0; i < rows; i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            int shiftOneElement = i;
            int j = 0;
            while (j < maxCountColumn) {
                InlineKeyboardButton button;
                if (shiftOneElement > sortedTime.size() - 1) {
                    button = createIgnoreButton(callbackPrefix, date);
                } else {
                    LocalTime time = sortedTime.get(shiftOneElement);
                    if (blockTime.contains(time)) {
                        button = createIgnoreButton(callbackPrefix, date);
                    } else {
                        button = TelegramHelper.createButton(callbackPrefix, date.atTime(time).toString(),
                                time.toString());
                    }
                }
                row.add(button);
                shiftOneElement = shiftOneElement + rows;
                j++;
            }
            keyboard.add(row);
        }
        return keyboard;
    }

    private InlineKeyboardButton createIgnoreButton(String callbackPrefix, LocalDate date) {
        return TelegramHelper.createControlButton(callbackPrefix, TelegramHelper.IGNORE,
                TelegramHelper.TELEGRAM_EMPTY, date.toString());
    }

    //TODO баг с зависанием, который лечится очищением бд
    //TODO Мастер календарь сохранять
}
