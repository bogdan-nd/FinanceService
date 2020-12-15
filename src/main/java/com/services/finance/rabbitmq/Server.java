package com.services.finance.rabbitmq;

import com.services.finance.controller.rest.dto.PaymentDTO;
import com.services.finance.entity.FinancePayment;
import com.services.finance.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Server {
    private final FinanceService financeService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void addPayment(PaymentDTO paymentDTO){
        FinancePayment payment = new FinancePayment(paymentDTO.getSenderId(),paymentDTO.getReceiverId(),
                paymentDTO.getMoneyAmount(),paymentDTO.getDescription());

        financeService.createPayment(payment);
    }
}
