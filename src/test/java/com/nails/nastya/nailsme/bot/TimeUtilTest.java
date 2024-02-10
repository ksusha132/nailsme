package com.nails.nastya.nailsme.bot;

import com.nails.nastya.nailsme.bot.util.TestHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TimeUtilTest {

    private final TimeUtil timeUtil = new TimeUtil();

    @Test
    void generateTimeKeyboardWithoutBlock() {
        String prefix = "0000";
        LocalDate currentDate = LocalDate.now();
        LocalTime startTime = LocalTime.MIN;
        LocalTime endTime = LocalTime.MAX;

        //When
        final int hours = (int) startTime.until(endTime, ChronoUnit.HOURS);
        Set<LocalTime> workTime = Stream.iterate(startTime, it -> it.plusHours(1))
                .limit(hours + 1L)
                .collect(Collectors.toSet());
        Set<LocalTime> blockTimes = Collections.EMPTY_SET;

        //Then
        List<List<InlineKeyboardButton>> result = timeUtil.generateTimeKeyboard(currentDate, workTime,
                blockTimes, prefix);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.size());
        TestHelper.checkDifferentButtonText(25, result);
        checkSizeArrays(result);
        checkIgnoreValues(currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), prefix,
                null, result.get(0).get(0));
        checkValues("00:00", prefix, currentDate, result.get(1).get(0));
        checkValues("01:00", prefix, currentDate, result.get(2).get(0));
        checkValues("02:00", prefix, currentDate, result.get(3).get(0));
        checkValues("03:00", prefix, currentDate, result.get(4).get(0));
        checkValues("04:00", prefix, currentDate, result.get(1).get(1));
        checkValues("05:00", prefix, currentDate, result.get(2).get(1));
        checkValues("06:00", prefix, currentDate, result.get(3).get(1));
        checkValues("07:00", prefix, currentDate, result.get(4).get(1));
        checkValues("08:00", prefix, currentDate, result.get(1).get(2));
        checkValues("09:00", prefix, currentDate, result.get(2).get(2));
        checkValues("20:00", prefix, currentDate, result.get(1).get(5));
        checkValues("21:00", prefix, currentDate, result.get(2).get(5));
        checkValues("22:00", prefix, currentDate, result.get(3).get(5));
        checkValues("23:00", prefix, currentDate, result.get(4).get(5));
    }

    @Test
    void generateTimeKeyboardWithBlock() {
        String prefix = "0000";
        LocalDate currentDate = LocalDate.now();
        LocalTime startTime = LocalTime.MIN;
        LocalTime endTime = LocalTime.MAX;

        //When
        final int hours = (int) startTime.until(endTime, ChronoUnit.HOURS);
        Set<LocalTime> workTime = Stream.iterate(startTime, it -> it.plusHours(1))
                .limit(hours + 1L)
                .collect(Collectors.toSet());
        Set<LocalTime> blockTimes = Set.of(LocalTime.parse("23:00"), LocalTime.parse("00:00"),
                LocalTime.parse("15:00"), LocalTime.parse("20:00"));

        //Then
        List<List<InlineKeyboardButton>> result = timeUtil.generateTimeKeyboard(currentDate, workTime,
                blockTimes, prefix);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.size());
        TestHelper.checkDifferentButtonText(22, result);
        checkSizeArrays(result);
        checkIgnoreValues(currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), prefix,
                null, result.get(0).get(0));
        checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, currentDate.toString(), result.get(1).get(0));
        checkValues("01:00", prefix, currentDate, result.get(2).get(0));
        checkValues("02:00", prefix, currentDate, result.get(3).get(0));
        checkValues("03:00", prefix, currentDate, result.get(4).get(0));
        checkValues("04:00", prefix, currentDate, result.get(1).get(1));
        checkValues("05:00", prefix, currentDate, result.get(2).get(1));
        checkValues("06:00", prefix, currentDate, result.get(3).get(1));
        checkValues("07:00", prefix, currentDate, result.get(4).get(1));
        checkValues("08:00", prefix, currentDate, result.get(1).get(2));
        checkValues("09:00", prefix, currentDate, result.get(2).get(2));
        checkValues("19:00", prefix, currentDate, result.get(4).get(4));
        checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, currentDate.toString(), result.get(4).get(5));
        checkValues("21:00", prefix, currentDate, result.get(2).get(5));
        checkValues("22:00", prefix, currentDate, result.get(3).get(5));
        checkIgnoreValues(TelegramHelper.TELEGRAM_EMPTY, prefix, currentDate.toString(), result.get(4).get(5));
    }

    private void checkIgnoreValues(String needText, String prefix, String value, InlineKeyboardButton button) {
        String needCallbackData = prefix + TelegramHelper.VALUES_SEPARATOR + TelegramHelper.IGNORE;
        if (StringUtils.isNotBlank(value)) {
            needCallbackData += TelegramHelper.VALUES_SEPARATOR + value;
        }
        Assertions.assertNotNull(button);
        Assertions.assertEquals(needText, button.getText());
        Assertions.assertEquals(needCallbackData, button.getCallbackData());
    }

    private void checkValues(String needText, String prefix, LocalDate currentDate, InlineKeyboardButton button) {
        String needCallbackData = prefix + TelegramHelper.VALUES_SEPARATOR +
                currentDate.atTime(LocalTime.parse(needText));
        Assertions.assertNotNull(button);
        Assertions.assertEquals(needText, button.getText());
        Assertions.assertEquals(needCallbackData, button.getCallbackData());
    }

    private void checkSizeArrays(List<List<InlineKeyboardButton>> collection) {
        Assertions.assertEquals(1, collection.get(0).size());
        for (int i = 1; i < collection.size(); i++) {
            Assertions.assertEquals(6, collection.get(i).size());
        }
    }
}