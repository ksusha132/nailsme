package com.nails.nastya.nailsme.bot;

import com.nails.nastya.nailsme.config.BotConfig;
import com.nails.nastya.nailsme.enumeration.Order;
import com.nails.nastya.nailsme.enumeration.OrderStep;
import com.nails.nastya.nailsme.persistance.UserOrder;
import com.nails.nastya.nailsme.persistance.UserOrderDetail;
import com.nails.nastya.nailsme.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    private final CalendarUtil calendarUtil;

    private final TimeUtil timeUtil;

    private final UserOrderService userOrderService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            processingDirectMessage(update);
        } else if (update.hasCallbackQuery()) {
            processingCallbackMenu(update);
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    private void processingDirectMessage(Update update) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (messageText) {
                case TelegramHelper.BOT_START -> {
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    //add registration (create client)
                }
                default -> {
                    // try to find id time slot by id if not - send default message
                    // запись Анастасия маникюр(ид услуги) 10.09.2021 10:00-12:00 (ид таймслота)
                }
            }
            sendMsgStartMenu(chatId, message.getMessageId());
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Приветствую, " + name + ", я Настин помощник!" + "\n" +
                "Вы можете с помощью меня записаться на маникюр, отменить запись, узнать когда вы записаны," + "\n" +
                "получить ссылку на инстаграмм Анастасии" + "\n" +
                "Когда Вы записываетесь, пожалуйста, указывайте услуги которые Вы хотите получить." + "\n" +
                "Например: маникюр с покрытием. Маникюр и педикюр, тоже самое с дизайном." + "\n";
        sendMessage(chatId, null, answer, null);
    }

    //Обработка ответов callback меню
    private void processingCallbackMenu(Update update) {
        //TODO проверить безопасность при пересылке (завязываться на chatID)
        if (TelegramHelper.isExistsCallbackData(update)) {

            String rawData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            if (StringUtils.equals(Order.APPOINT_RESERVE.toString(), rawData)) {
                //Переделать на клиента, чтобы chatId клиентИд были одно и то же
                createAppointmentReserveOrder(chatId, messageId);
            } else {
                //Если совпадений c Order не нашлось, значит, пришло значение для заполнения деталей
                processingComeDetails(chatId, messageId, rawData);
            }
        }
    }

    private void createAppointmentReserveOrder(long chatId, Integer messageId) {
        Integer orderId = userOrderService.createUserOrder(chatId, Order.APPOINT_RESERVE);
        sendMasterChooseInline(chatId, messageId, orderId);
    }

    private void sendTime(long chatId,
                          Integer messageId,
                          LocalDate dateStart,
                          String text,
                          String prefix,
                          Set<LocalTime> workTimes,
                          Set<LocalTime> blockTimes) {
        // Создаем клавиатуру
        InlineKeyboardMarkup inlineKeyboardMarkup = createTimeKeyboard(dateStart, prefix, workTimes, blockTimes);
        sendMessage(chatId, messageId, text, inlineKeyboardMarkup);
    }

    private void sendCalendar(long chatId,
                              Integer messageId,
                              LocalDate dateStart,
                              String text,
                              String prefix,
                              Set<LocalDate> blockData) {
        // Создаем клавиатуру календаря
        InlineKeyboardMarkup inlineKeyboardMarkup = createCalendarKeyboard(dateStart, prefix, blockData);
        sendMessage(chatId, messageId, text, inlineKeyboardMarkup);
    }

    public void processingComeDetails(long chatId, Integer messageId, String rawData) {
        Integer detailId = extractDetailId(rawData);
        if (userOrderService.isOrderNotCompleted(detailId)) {
            saveEnteredValueForCurrentDetail(detailId, rawData);
            startingNextStepDetails(chatId, messageId, detailId);
        } else {
            String text = "Это сообщение относится к завершенной заявке.\r\nПожалуйста, создайте новую заявку.";
            sendMessage(chatId, null, text, null);
            sendMsgStartMenu(chatId, messageId);
        }
    }

    private void saveEnteredValueForCurrentDetail(Integer detailId, String rawData) {
        String value = extractValue(rawData);
        userOrderService.fillingValue(detailId, value);
    }

    private void processingReserveCalendar(long chatId, Integer messageId, Integer detailId) {
        UserOrder order = userOrderService.findOrderByChatId(chatId);
        String master = TelegramHelper.extractValueFromOrderByState(order,
                OrderStep.APPOINT_RESERVE_START_CHOOSE_MASTER);
        String date = TelegramHelper.extractValueFromOrderByState(order,
                OrderStep.APPOINT_RESERVE_CALENDAR);
        if (CalendarUtil.CALENDAR_CONTROL.stream()
                .anyMatch(it -> StringUtils.startsWith(date, it))) {
            String controlKey = StringUtils.substringBefore(date, TelegramHelper.VALUES_SEPARATOR);
            if (!StringUtils.equals(TelegramHelper.IGNORE, controlKey)) {
                LocalDate localDate = LocalDate.parse(StringUtils.substringAfter(date, TelegramHelper.VALUES_SEPARATOR));
                LocalDate shiftDate = localDate;
                if (StringUtils.equals(CalendarUtil.LATER, controlKey)) {
                    shiftDate = localDate.plus(Period.ofMonths(1));
                } else if (StringUtils.equals(CalendarUtil.EARLIER, controlKey)) {
                    shiftDate = localDate.minus(Period.ofMonths(1));
                }
                Set<LocalDate> blockData = Set.of(); //TODO получить список блокированных дат для вывода для master
                sendCalendar(chatId, messageId, shiftDate, "Календарь записи для: " + master,
                        detailId.toString(), blockData);
            }
        } else {
            Integer detailIdNew = userOrderService.getUserOrderIdDetailOrCreateNew(order.getId(),
                    OrderStep.APPOINT_RESERVE_TIME);
            //TODO сделать заполнение реального времени
            LocalTime startTime = LocalTime.MIN;
            LocalTime endTime = LocalTime.MAX;
            final int hours = (int) startTime.until(endTime, ChronoUnit.HOURS);
            Set<LocalTime> workTime = Stream.iterate(startTime, it -> it.plusHours(1))
                    .limit(hours + 1L)
                    .collect(Collectors.toSet());
            //Set<LocalTime> blockTimes = Set.of(LocalTime.parse("15:00"));
            Set<LocalTime> blockTimes = Collections.EMPTY_SET;
            sendTime(chatId, messageId, LocalDate.parse(date), "Выберите время", detailIdNew.toString(), workTime, blockTimes);
        }
    }

    private void sendSuccessReservation(long chatId, Integer messageId, String master, String date, String time) {
        String text = "Успешно записаны.\r\nМастер " + master + " будет ждать Вас " + date + " в " + time;
        sendMessage(chatId, messageId, text, null);
    }

    private Integer extractDetailId(String rawData) {
        return Integer.parseInt(StringUtils.substringBefore(rawData, TelegramHelper.VALUES_SEPARATOR));
    }

    private String extractValue(String newValue) {
        return StringUtils.substringAfter(newValue, TelegramHelper.VALUES_SEPARATOR);
    }

    //Обработка деталей заказа
    public void startingNextStepDetails(long chatId,
                                        Integer messageId,
                                        Integer detailId) {

        OrderStep orderStep = userOrderService.getCurrentState(detailId);
        switch (orderStep) {
            case APPOINT_RESERVE_START_CHOOSE_MASTER -> processReserveChoseMaster(chatId, messageId, detailId);
            case APPOINT_RESERVE_CALENDAR -> processingReserveCalendar(chatId, messageId, detailId);
            case APPOINT_RESERVE_TIME -> processReserveTime(chatId, messageId);
            case APPOINT_RESERVE_START_CHOOSE_SERVICE -> throw new RuntimeException("Не готово");
        }
    }

    private void processReserveChoseMaster(long chatId, Integer messageId, Integer detailId) {
        Integer orderId = userOrderService.getOrderIdByDetailId(detailId);
        Integer calendarDetailId = userOrderService.getUserOrderIdDetailOrCreateNew(orderId,
                OrderStep.APPOINT_RESERVE_CALENDAR);
        UserOrder order = userOrderService.findOrderByChatId(chatId); //ПО чат?
        String master = TelegramHelper.extractValueFromOrderByState(order,
                OrderStep.APPOINT_RESERVE_START_CHOOSE_MASTER);

        Set<LocalDate> blockData = Set.of(); //TODO получить список блокированных дат для вывода
        sendCalendar(chatId, messageId, LocalDate.now(), "Календарь записи для: " + master,
                calendarDetailId.toString(), blockData);
    }

    private void processReserveTime(long chatId, Integer messageId) {
        UserOrder order = userOrderService.findOrderByChatId(chatId);
        String data = TelegramHelper.extractValueFromOrderByState(order, OrderStep.APPOINT_RESERVE_TIME);

        if (TimeUtil.CALENDAR_CONTROL.stream()
                .noneMatch(it -> StringUtils.startsWith(data, it))) {
            String master = TelegramHelper.extractValueFromOrderByState(order,
                    OrderStep.APPOINT_RESERVE_START_CHOOSE_MASTER);

            UserOrderDetail calendarDetail = order.getDetails().stream()
                    .filter(it -> Objects.equals(it.getState(), OrderStep.APPOINT_RESERVE_CALENDAR))
                    .findFirst().orElseThrow();

            String date = Optional.ofNullable(calendarDetail)
                    .map(UserOrderDetail::getValueField)
                    .orElseThrow();

            String choseData = TelegramHelper.extractValueFromOrderByState(order,
                    OrderStep.APPOINT_RESERVE_TIME);
            LocalDateTime chooseData = Optional.ofNullable(choseData)
                    .map(LocalDateTime::parse)
                    .orElseThrow();

            if (!Objects.equals(LocalDate.parse(date), chooseData.toLocalDate())) {
                date = chooseData.toLocalDate().toString();
                userOrderService.fillingValue(calendarDetail.getId(), date);
            }

            String time = Optional.ofNullable(chooseData)
                    .map(LocalDateTime::toLocalTime)
                    .map(LocalTime::toString)
                    .orElseThrow();
            userOrderService.toCompleteOrder(order.getId());
            sendSuccessReservation(chatId, messageId, master, date, time);
        }
    }

    private void sendMsgStartMenu(Long chatId, Integer messageId) {
        // Создаем главное меню
        InlineKeyboardMarkup replyKeyboardMarkup = createMainMenuKeyboard();
        sendMessage(chatId, messageId, "Меню", replyKeyboardMarkup);
    }

    private InlineKeyboardMarkup createTimeKeyboard(LocalDate dateStart,
                                                    String prefix,
                                                    Set<LocalTime> workTimes,
                                                    Set<LocalTime> blockTimes) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(timeUtil.generateTimeKeyboard(dateStart, workTimes, blockTimes, prefix));
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup createCalendarKeyboard(LocalDate dateStart, String prefix, Set<LocalDate> blockData) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(calendarUtil.generateCalendarKeyboard(dateStart, prefix,
                blockData, LocalDate.now()));
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup createMainMenuKeyboard() {
        InlineKeyboardButton keyboardFirstRow1 = TelegramHelper.createButton(null,
                Order.APPOINT_RESERVE.toString(), "Запись");

        InlineKeyboardButton keyboardFirstRow2 = TelegramHelper.createButton(null,
                Order.APPOINT_RESERVE_CANCELLING.toString(), "Отмена записи");

        InlineKeyboardButton keyboardFirstRow3 = TelegramHelper.createButton(null,
                Order.APPOINT_RESERVE_RESCHEDULING.toString(), "Перенос записи");

        InlineKeyboardButton keyboardFirstRow4 = TelegramHelper.createButton(null,
                Order.CONTACT_MASTER.toString(), "Получить контакты мастера");

        // Создаем список строк клавиатуры и добавляем все строчки клавиатуры в списки
        List<InlineKeyboardButton> keyboard1 = List.of(keyboardFirstRow1, keyboardFirstRow2);
        List<InlineKeyboardButton> keyboard2 = List.of(keyboardFirstRow3, keyboardFirstRow4);
        // и устанавливаем эти списки нашей клавиатуре
        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(keyboard1, keyboard2));
        return replyKeyboardMarkup;
    }

    private void sendMasterChooseInline(long chatId, Integer messageId, Integer orderId) {
        Integer detailId = userOrderService.getUserOrderIdDetailOrCreateNew(orderId,
                OrderStep.APPOINT_RESERVE_START_CHOOSE_MASTER);
        // Создаем клавиатуру
        //TODO получить список мастеров и применить в следующем методе
        InlineKeyboardMarkup masterKeyboardMark = createMasterChooseKeyboard(detailId);
        sendMessage(chatId, messageId, "Выберите мастера: ", masterKeyboardMark);
    }

    private InlineKeyboardMarkup createMasterChooseKeyboard(Integer detailId) {
        List<List<InlineKeyboardButton>> mastersButtons = new ArrayList<>();
        List<InlineKeyboardButton> masters = new ArrayList<>();
        masters.add(TelegramHelper.createButton(detailId.toString(), "Анастасия", "Анастасия"));
        masters.add(TelegramHelper.createButton(detailId.toString(), "Наталья", "Наталья"));
        // Добавляем все строчки клавиатуры в список
        mastersButtons.add(masters);

        InlineKeyboardMarkup masterKeyboardMark = new InlineKeyboardMarkup();
        // и устанавливаем этот список нашей клавиатуре
        masterKeyboardMark.setKeyboard(mastersButtons);
        return masterKeyboardMark;
    }

    private void sendMessage(Long chatId,
                             Integer messageId,
                             String textToSend,
                             InlineKeyboardMarkup masterKeyboardMark) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        Optional.ofNullable(chatId).ifPresent(sendMessage::setChatId);
        Optional.ofNullable(messageId).ifPresent(sendMessage::setReplyToMessageId);
        Optional.ofNullable(masterKeyboardMark).ifPresent(sendMessage::setReplyMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
