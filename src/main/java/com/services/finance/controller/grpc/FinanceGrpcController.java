package com.services.finance.controller.grpc;

import com.services.finance.entity.FinancePayment;
import com.services.finance.service.FinanceService;
import com.services.grpc.server.finance.*;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@GrpcService
@AllArgsConstructor
public class FinanceGrpcController extends FinanceServiceGrpc.FinanceServiceImplBase {
    private final FinanceService financeService;

    @Override
    public void getClientPayments(PaymentIdRequest request, StreamObserver<PaymentResponse> responseObserver) {
        UUID clientId = UUID.fromString(request.getId());
        List<FinancePayment> payments = financeService.getClientPayments(clientId);
        List<ProtoFinance> protoPayments = transformPaymentToProto(payments);

        PaymentResponse response = PaymentResponse.newBuilder()
                .addAllPayments(protoPayments).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createPayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        UUID senderId = UUID.fromString(request.getSenderId());
        UUID receiverId = UUID.fromString(request.getReceiverId());

        FinancePayment payment = new FinancePayment(senderId,receiverId,
                request.getMoneyAmount(),request.getDescription());

        ProtoFinance protoPayment = paymentToProto(payment);
        PaymentResponse response = PaymentResponse.newBuilder()
                .addPayments(protoPayment).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private ProtoFinance paymentToProto(FinancePayment payment){
        String paymentId = payment.getId().toString();
        String senderId = payment.getSenderId().toString();
        String receiverId = payment.getReceiverId().toString();

        return ProtoFinance.newBuilder()
                .setId(paymentId)
                .setSenderId(senderId)
                .setReceiverId(receiverId)
                .setMoneyAmount(payment.getMoneyAmount())
                .setDescription(payment.getDescription())
                .build();
    }

    private List<ProtoFinance> transformPaymentToProto(List<FinancePayment> payments){
        List<ProtoFinance> protoFinances = new ArrayList<>();
        for(FinancePayment payment:payments){
            ProtoFinance protoTraining = paymentToProto(payment);
            protoFinances.add(protoTraining);
        }
        return protoFinances;
    }
}
