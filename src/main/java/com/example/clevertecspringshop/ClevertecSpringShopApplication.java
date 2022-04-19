package com.example.clevertecspringshop;

import com.example.clevertecspringshop.service.CardService;
import com.example.clevertecspringshop.check.CheckOutput;
import com.example.clevertecspringshop.service.ProductService;
import com.example.clevertecspringshop.service.ReceiptService;
import com.example.clevertecspringshop.service.email.EmailSenderService;
import com.example.clevertecspringshop.service.listeners.EventManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClevertecSpringShopApplication {

        CardService cardService;
        ProductService productService;
        ReceiptService receiptService;
        EmailSenderService emailSenderService;
        EventManager eventManager;

    public static void main(String[] args) {
        SpringApplication.run(ClevertecSpringShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            CheckOutput check = new CheckOutput(cardService, productService, receiptService, eventManager,emailSenderService);
            check.print(6666);
        };
    }
}
