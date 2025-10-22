package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingServiceGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

    // localhost:9001/BillingService/CreatePatientAccount
    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String serviceAddress,
                                    @Value("${billing.service.grpc.port:9001}") int grpcPort) {

        log.debug("connecting to billing service grpc server at {}:{}", serviceAddress, grpcPort);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(serviceAddress, grpcPort)
                .usePlaintext().build();
        billingServiceBlockingStub = BillingServiceGrpc.newBlockingStub(managedChannel);

    }

    public BillingResponse createBillingAccount(String patientId,String name, String email){
        BillingRequest billingRequest = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();
        BillingResponse billingResponse = billingServiceBlockingStub.createBillingAccount(billingRequest);
        log.info("received response from billing service via grpc: {}", billingResponse);
        return billingResponse;
    }
}
