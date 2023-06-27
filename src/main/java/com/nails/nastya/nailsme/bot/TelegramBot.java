package com.nails.nastya.nailsme.bot;

import com.nails.nastya.nailsme.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

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
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                switch (messageText) {
                    case "/start" -> {
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        sendMsg(message, "Это команда старт!");
                        System.out.println(message.getText());
                    }
                    case "Запись" -> {
                        sendMasterChoose(message, "Выберите мастера: ");
                    }
                    case "Анастасия" -> {
                        sendPrice(message);
                        sendText(message, "Выберите услугу из прайс листа выше, пожалуйста" +
                                " введите номер, например 1");
                    }
                    case "1" -> {
                        sendWindowChoose(message, "Выберите окошко для записи Анастасии: ");
                        // get from db already taken windows
                        // get difference
                        // get string windows with id
                    }
                    case "сегодня" -> {
                        sendTimeTable(message);
                        // get from db already taken windows
                        // get difference
                        // get string windows with id
                    }
                    case "Отмена" -> {
                        sendMsg(message, "Отмена записи");
                        System.out.println(message.getText());
                    }
                    case "Перенос" -> {
                        sendMsg(message, "Перенос записи");
                        System.out.println(message.getText());
                    }
                    case "Контакты" -> {
                        sendMsg(message, "Получить контакты мастера");
                        System.out.println(message.getText());
                    }
                    case "Флуд" -> {
                        sendMsg(message, "Побалтать с ботом PS - спроси чат GPT про маникюр");
                        System.out.println(message.getText());
                    }
                    default -> {
                        // try to find id time slot by id if not - send default message

                        // запись Анастасия маникюр(ид услуги) 10.09.2021 10:00-12:00 (ид таймслота)
                        sendMsg(message, "Это дефолт! Брейк!");
                        System.out.println(message.getText());
                    }
                }
            }
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

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Запись");
        keyboardFirstRow.add("Отмена записи");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Перенос записи");
        keyboardSecondRow.add("Получить контакты мастера");


        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Приветствую, " + name + ", я Настин помошник!" + "\n" +
                "Вы можете с помощью меня записаться на маникюр, отменить запись, узнать когда вы записаны," + "\n" +
                "получить сслку на инстаграмм Анастасии" + "\n" +
                "Когда Вы записываетесь, пожалуйста, указываете услуги которые Выхотите получить." + "\n" +
                "Например: маникюр с покрытием. Маникюр и педикюр, тоже самое с дизайном." + "\n";
        sendMessage(chatId, answer);
    }

    public void sendText(Message message, String text) {
        sendMessage(message.getChatId(), text);
    }

    public void sendMasterChoose(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Анастасия");
        keyboardFirstRow.add("Наталья");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendPrice(Message message) {
        SendMessage sendMessage = new SendMessage();
        String text = "Маникюр - 2200 -  Номер 1" + "\n"
                + "Педикюр - 3300 Номер 2" + "\n"
                + "Что-то еще 1000 Номер 3";
        sendMessage.setText(text);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendTimeTable(Message message) {
        SendMessage sendMessage = new SendMessage();
        String text = "10 00 - 12 00 Номер 11" + "\n"
                + "12 00 - 14 00 Номер 13" + "\n"
                + "14 00 - 16 00 Номер 13";
        sendMessage.setText(text);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendWindowChoose(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Сегодня");
        keyboardFirstRow.add("Завтра");
        keyboardFirstRow.add("Неделя");
        keyboardFirstRow.add("Месяц");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}
