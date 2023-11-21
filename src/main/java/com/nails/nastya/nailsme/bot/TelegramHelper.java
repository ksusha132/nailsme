package com.nails.nastya.nailsme.bot;

import com.nails.nastya.nailsme.enumeration.OrderStep;
import com.nails.nastya.nailsme.persistance.UserOrder;
import com.nails.nastya.nailsme.persistance.UserOrderDetail;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Objects;

@UtilityClass
public class TelegramHelper {

    public static final String VALUES_SEPARATOR = ";";
    public static final String TELEGRAM_EMPTY = " ";
    public static final String BOT_START = "/start";

    public static final String IGNORE = "ignore!@#$%^&";

    public InlineKeyboardButton createButton(String callbackPrefix, String callBack, String text) {
        InlineKeyboardButton bt = new InlineKeyboardButton();
        bt.setText(text);  //Отображение кнопки
        bt.setCallbackData(StringUtils.isNotBlank(callbackPrefix) ?
                callbackPrefix + VALUES_SEPARATOR + callBack : callBack);
        return bt;
    }

    public InlineKeyboardButton createControlButton(String callbackPrefix,
                                                    String callBack,
                                                    String text,
                                                    String data) {
        InlineKeyboardButton bt = new InlineKeyboardButton();
        bt.setText(text);  //Отображение кнопки
        String callbackData = Objects.nonNull(callbackPrefix) ? callbackPrefix + VALUES_SEPARATOR + callBack : callBack;
        if (StringUtils.isNoneBlank(data)) {
            callbackData += VALUES_SEPARATOR + data;
        }
        bt.setCallbackData(callbackData);
        return bt;
    }

    public String extractValueFromOrderByState(UserOrder order, OrderStep step) {
        return order.getDetails().stream()
                .filter(it -> Objects.equals(it.getState(), step))
                .findFirst()
                .map(UserOrderDetail::getValueField)
                .orElseThrow();
    }

    public static boolean isExistsCallbackData(Update update) {
        return Objects.nonNull(update) && update.hasCallbackQuery()
                && StringUtils.isNotBlank(update.getCallbackQuery().getData())
                && !StringUtils.contains(update.getCallbackQuery().getData(), TelegramHelper.IGNORE);
    }
}
