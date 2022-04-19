package com.example.clevertecspringshop.service.listeners;

import org.springframework.mail.MailAuthenticationException;

public interface EventListener  {
    void update(String eventType, String object) throws MailAuthenticationException;
}