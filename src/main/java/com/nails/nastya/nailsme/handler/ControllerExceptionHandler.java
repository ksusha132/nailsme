package com.nails.nastya.nailsme.handler;

import com.nails.nastya.nailsme.constant.MessageConst;
import com.nails.nastya.nailsme.exception.AppointmentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({AppointmentException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpEntity<ErrorBody> handleAppointmentException(AppointmentException ex) {
        log.error("", ex);
        String message = String.format(messageSource.getMessage(MessageConst.GENERAL_ERROR, new Object[]{ex.getMessage()},
                Locale.US), ex.getMessage());
        return logAndCreateHttpEntity(message, ex);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpEntity<ErrorBody> handleAll(Exception ex) {
        log.error("", ex);
        String message = String.format(messageSource.getMessage(MessageConst.GENERAL_ERROR, new Object[]{ex.getMessage()},
                Locale.US), ex.getMessage());
        return logAndCreateHttpEntity(message, ex);
    }

    private HttpEntity logAndCreateHttpEntity(String message, Throwable ex) {
        log.error(message, ex);
        return new HttpEntity<>(message);
    }
}
