package com.nails.nastya.nailsme.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * AppMessagesConfig application service messages configuration.
 */
@Configuration
public class AppMessagesConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/error_messages");
        return messageSource;
    }
}

