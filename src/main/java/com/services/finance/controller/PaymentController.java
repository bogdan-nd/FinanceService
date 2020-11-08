package com.services.finance.controller;

import com.services.finance.dto.PaymentDTO;
import com.services.finance.entity.FinancePayment;
import com.services.finance.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "payments")
@AllArgsConstructor

public class PaymentController {
    private final FinanceService service;

    @GetMapping(value = "clients", params = "{clientId}")
    public ResponseEntity<List<FinancePayment>> getClientPayments(@PathVariable UUID clientId){
        return ResponseEntity.ok(service.getClientPayments(clientId));
    }


    @PostMapping
    public ResponseEntity<FinancePayment> createPayment(@PathVariable PaymentDTO dto){
        FinancePayment payment = new FinancePayment(dto.getSenderId(),dto.getReceiverId(),
                dto.getMoneyAmount(),dto.getDescription());

        return ResponseEntity.ok(service.createPayment(payment));
    }

    @PutMapping
    public ResponseEntity<FinancePayment> savePayment(@PathVariable FinancePayment payment){
        return ResponseEntity.ok(service.savePayment(payment));
    }
}
