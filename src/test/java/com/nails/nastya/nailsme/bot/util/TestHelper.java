package com.nails.nastya.nailsme.bot.util;


import com.nails.nastya.nailsme.bot.TelegramHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collection;
import java.util.List;

public class TestHelper {

    public static void checkIgnoreValues(String needText, String prefix, String value, InlineKeyboardButton button) {
        String needCallbackData = prefix + TelegramHelper.VALUES_SEPARATOR + TelegramHelper.IGNORE;
        if (StringUtils.isNotBlank(value)) {
            needCallbackData += TelegramHelper.VALUES_SEPARATOR + value;
        }
        Assertions.assertNotNull(button);
        Assertions.assertEquals(needText, button.getText());
        Assertions.assertEquals(needCallbackData, button.getCallbackData());
    }

    public static void checkDifferentButtonText(int mustSize, List<List<InlineKeyboardButton>> keys) {
        List<String> result = keys.stream()
                .flatMap(Collection::stream)
                .map(InlineKeyboardButton::getText)
                .distinct()
                .toList();
        Assertions.assertEquals(mustSize, result.size());
    }
}
