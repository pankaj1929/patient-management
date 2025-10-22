package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    @Override
    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
       log.info("createBillingAccount request received = {}",billingRequest.toString());

       BillingResponse billingResponse = BillingResponse.newBuilder()
               .setAccountId("12345")
               .setStatus("success")
               .build();
       responseObserver.onNext(billingResponse);
       responseObserver.onCompleted();
    }
}
