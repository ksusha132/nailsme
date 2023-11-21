package com.nails.nastya.nailsme.bot;

import com.nails.nastya.nailsme.bot.util.TestHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

class CalendarUtilTest {

    private final CalendarUtil calendarUtil = new CalendarUtil();

    @Test
    void generateCalendarKeyboard() {

        //When
        LocalDate date = LocalDate.parse("2023-11-20");
        String prefix = "0000";
        Set<LocalDate> blockData = Set.of(LocalDate.parse("2023-11-23"));

        //Then
        List<List<InlineKeyboardButton>> result = calendarUtil.generateCalendarKeyboard(date, prefix, blockData, date);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(8, result.size());
        TestHelper.checkDifferentButtonText(20, result);
        checkSizeArrays(result);
        TestHelper.checkIgnoreValues(date.format(DateTimeFormatter.ofPattern("MMM yyyy")), prefix,
                null, result.get(0).get(0));
        TestHelper.checkIgnoreValues("пн", prefix, null, result.get(1).get(0));
        TestHelper.checkIgnoreValues("вт", prefix, null, result.get(1).get(1));
        TestHelper.checkIgnoreValues("ср", prefix, null, result.get(1).get(2));
        TestHelper.checkIgnoreValues("пт", prefix, null, result.get(1).get(4));
        TestHelper.checkIgnoreValues("сб", prefix, null, result.get(1).get(5));
        TestHelper.checkIgnoreValues("вс", prefix, null, result.get(1).get(6));
        TestHelper.checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, null, result.get(2).get(0));
        TestHelper.checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, null, result.get(4).get(6));
        checkValues(prefix, date.toString(), result.get(5).get(0), null);
        checkValues(prefix, "2023-11-21", result.get(5).get(1), null);
        checkValues(prefix, "2023-11-22", result.get(5).get(2), null);
        TestHelper.checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, null, result.get(5).get(3));
        checkValues(prefix, "2023-11-24", result.get(5).get(4), null);
        checkValues(prefix, "2023-11-29", result.get(6).get(2), null);
        checkValues(prefix, "2023-11-30", result.get(6).get(3), null);
        TestHelper.checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, null, result.get(6).get(4));
        TestHelper.checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, null, result.get(6).get(5));
        TestHelper.checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, null, result.get(6).get(6));
        TestHelper.checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, null, result.get(7).get(0));
        checkValues(prefix, date.toString(), result.get(7).get(1), CalendarUtil.LATER);
    }

    public static void checkValues(String prefix, String needDate, InlineKeyboardButton button, String symbol) {
        String needCallbackData = prefix;
        LocalDate localDate = LocalDate.parse(needDate);
        String text = String.valueOf(localDate.getDayOfMonth());
        if (StringUtils.isNotBlank(symbol)) {
            needCallbackData += TelegramHelper.VALUES_SEPARATOR + symbol;
            text = symbol;
        }
        needCallbackData += TelegramHelper.VALUES_SEPARATOR + localDate;
        Assertions.assertNotNull(button);
        Assertions.assertEquals(text, button.getText());
        Assertions.assertEquals(needCallbackData, button.getCallbackData());
    }

    private void checkSizeArrays(List<List<InlineKeyboardButton>> collection) {
        Assertions.assertEquals(1, collection.get(0).size());
        for (int i = 1; i < collection.size() - 1; i++) {
            Assertions.assertEquals(7, collection.get(i).size());
        }
        Assertions.assertEquals(2, collection.get(collection.size() - 1).size());
    }
}