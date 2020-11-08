package com.services.finance.service;

import com.services.finance.entity.FinancePayment;
import com.services.finance.repo.PaymentRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FinanceService{
    private final PaymentRepository repository;

    @Transactional
    public FinancePayment createPayment(FinancePayment payment){
        return repository.save(payment);
    }


    @Transactional
    public List<FinancePayment> getClientPayments(UUID clientId){
        return repository.getClientPayments(clientId);
    }
}
