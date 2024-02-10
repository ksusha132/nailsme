package com.nails.nastya.nailsme.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class CalendarUtil {
    public static final String LATER = ">";
    public static final String EARLIER = "<";

    public static final String[] WD = {"пн", "вт", "ср", "чт", "пт", "сб", "вс"};

    public static final Set<String> CALENDAR_CONTROL = Set.of(LATER, EARLIER, TelegramHelper.IGNORE);

    //TODO Ограничение, что в прошлом нельзя записаться,
    // При недоступных данных, не выводить даты и услуги
    public List<List<InlineKeyboardButton>> generateCalendarKeyboard(LocalDate date,
                                                                     String callbackPrefix,
                                                                     Set<LocalDate> blockData,
                                                                     LocalDate currentDate) {

        if (date == null) {
            return Collections.emptyList();
        }

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // row - Month and Year
        List<InlineKeyboardButton> headerRow = new ArrayList<>();
        headerRow.add(TelegramHelper.createControlButton(callbackPrefix, TelegramHelper.IGNORE,
                date.format(DateTimeFormatter.ofPattern("MMM yyyy")), null));
        keyboard.add(headerRow);

        // row - Days of the week
        List<InlineKeyboardButton> daysOfWeekRow = new ArrayList<>();
        for (String day : WD) {
            daysOfWeekRow.add(TelegramHelper.createControlButton(callbackPrefix, TelegramHelper.IGNORE,
                    day, null));
        }
        keyboard.add(daysOfWeekRow);

        LocalDate firstDay = date.withDayOfMonth(1);

        int shift = firstDay.getDayOfWeek().getValue() - 1;
        int daysInMonth = firstDay.lengthOfMonth();
        int rows = ((daysInMonth + shift) % 7 > 0 ? 1 : 0) + (daysInMonth + shift) / 7;

        for (int i = 0; i < rows; i++) {
            keyboard.add(buildRow(firstDay, shift, callbackPrefix, currentDate, blockData));
            firstDay = firstDay.plusDays(7 - shift);
            shift = 0;
        }

        List<InlineKeyboardButton> controlsRow = new ArrayList<>();
        if (currentDate.withDayOfMonth(1).isBefore(date.withDayOfMonth(1))) {
            controlsRow.add(TelegramHelper.createControlButton(callbackPrefix, EARLIER, EARLIER, date.toString()));
        } else {
            controlsRow.add(TelegramHelper.createControlButton(callbackPrefix, TelegramHelper.IGNORE,
                    TelegramHelper.TELEGRAM_EMPTY, null));
        }

        controlsRow.add(TelegramHelper.createControlButton(callbackPrefix, LATER, LATER, date.toString()));
        keyboard.add(controlsRow);
        return keyboard;
    }

    private List<InlineKeyboardButton> buildRow(LocalDate date,
                                                int shift,
                                                String callbackPrefix,
                                                LocalDate currentDate,
                                                Set<LocalDate> blockData) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        int day = date.getDayOfMonth();
        LocalDate callbackDate = date;
        for (int j = 0; j < shift; j++) {
            row.add(TelegramHelper.createControlButton(callbackPrefix, TelegramHelper.IGNORE,
                    TelegramHelper.TELEGRAM_EMPTY, null));
        }
        for (int j = shift; j < 7; j++) {
            if (day <= (date.lengthOfMonth()) && (!callbackDate.isBefore(currentDate))
                    && !blockData.contains(callbackDate)) {
                row.add(TelegramHelper.createButton(callbackPrefix, callbackDate.toString(), Integer.toString(day)));
            } else {
                row.add(TelegramHelper.createControlButton(callbackPrefix, TelegramHelper.IGNORE,
                        TelegramHelper.TELEGRAM_EMPTY, null));
            }
            callbackDate = callbackDate.plusDays(1);
            day++;
        }
        return row;
    }
}
