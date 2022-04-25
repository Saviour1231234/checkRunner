package com.example.clevertecspringshop;

import com.example.clevertecspringshop.repository.CardRepository;
import com.example.clevertecspringshop.repository.ProductRepository;
import com.example.clevertecspringshop.repository.ReceiptRepository;
import com.example.clevertecspringshop.repository.dao.CardDao;
import com.example.clevertecspringshop.repository.dao.ProductDao;
import com.example.clevertecspringshop.repository.dao.ReceiptDao;
import com.example.clevertecspringshop.service.CardService;
import com.example.clevertecspringshop.service.CheckOutput;
import com.example.clevertecspringshop.service.ProductService;
import com.example.clevertecspringshop.service.ReceiptService;
import com.example.clevertecspringshop.utils.DBUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClevertecSpringShopApplication {
//    ReceiptRepository receiptRepository;
//    CardRepository cardRepository;
//    ProductRepository productRepository;



    public static void main(String[] args) {

        SpringApplication.run(ClevertecSpringShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AnnotationConfigApplicationContext ctx) {
        return args -> {
            CardDao cardDao = ctx.getBean(CardDao.class);
            ProductDao productDao = ctx.getBean(ProductDao.class);
            ReceiptDao receiptDao = ctx.getBean(ReceiptDao.class);

            CheckOutput check = new CheckOutput(cardDao, productDao, receiptDao);
            check.print(1111);
        };
    }
}
