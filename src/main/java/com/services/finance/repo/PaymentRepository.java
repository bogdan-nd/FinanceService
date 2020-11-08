package com.services.finance.repo;

import com.services.finance.entity.FinancePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<FinancePayment, UUID> {
    @Query(value = "SELECT * from Payments p where p.senderId = :clientId", nativeQuery = true)
    List<FinancePayment> getClientPayments(@Param("clientId") UUID id);
}
