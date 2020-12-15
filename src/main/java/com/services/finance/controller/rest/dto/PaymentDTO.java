package com.services.finance.controller.rest.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentDTO {
    private UUID senderId;
    private UUID receiverId;
    private int moneyAmount;
    private String description;
}
