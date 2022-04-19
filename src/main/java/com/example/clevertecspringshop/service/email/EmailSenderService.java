package com.example.clevertecspringshop.service.email;

import com.example.clevertecspringshop.service.listeners.EventListener;

public interface EmailSenderService extends EventListener {

    void sendEmail(String subject, String message);

}
