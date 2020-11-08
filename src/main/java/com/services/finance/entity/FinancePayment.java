package com.services.finance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity(name = "Payments")
@NoArgsConstructor
@AllArgsConstructor
public class FinancePayment{
    @Id
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private int moneyAmount;
    private String description;

    public FinancePayment(UUID senderId, UUID receiverId, int moneyAmount, String description){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.moneyAmount = moneyAmount;
        this.description = description;
    }
}
