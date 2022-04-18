package com.example.clevertecspringshop.service;

import com.example.clevertecspringshop.entity.Receipt;
import com.example.clevertecspringshop.repository.ReceiptRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ReceiptService(ReceiptRepository receiptRepository) {

    public List<Receipt> findAllReceipts() {
        return receiptRepository.findAll();
    }
}
