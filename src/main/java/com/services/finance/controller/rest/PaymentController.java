package com.services.finance.controller.rest;

import com.services.finance.dto.PaymentDTO;
import com.services.finance.entity.FinancePayment;
import com.services.finance.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("payments")
@AllArgsConstructor

public class PaymentController {
    private final FinanceService service;

    @GetMapping(value = "clients/{clientId}")
    public ResponseEntity<List<FinancePayment>> getClientPayments(@PathVariable UUID clientId){
        return ResponseEntity.ok(service.getClientPayments(clientId));
    }


    @PostMapping
    public ResponseEntity<FinancePayment> createPayment(@RequestBody PaymentDTO dto){
        FinancePayment payment = new FinancePayment(dto.getSenderId(),dto.getReceiverId(),
                dto.getMoneyAmount(),dto.getDescription());

        return ResponseEntity.ok(service.createPayment(payment));
    }
}
