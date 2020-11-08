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
@NoArgsConstructor
public class FinanceService{
    private PaymentRepository repository;

    @Transactional
    public FinancePayment createPayment(FinancePayment payment){
        return repository.save(payment);
    }

    @Transactional
    public FinancePayment savePayment(FinancePayment payment){
        return repository.save(payment);
    }

    @Transactional
    public FinancePayment getById(UUID id) throws NotFoundException{
        Optional<FinancePayment> payment = repository.findById(id);

        if(payment.isPresent())
            return payment.get();
        else
            throw new NotFoundException(String.format("Payment with %s id does not exits", id));
    }

    @Transactional
    public List<FinancePayment> getClientPayments(UUID clientId){
        return repository.getClientPayments(clientId);
    }
}
