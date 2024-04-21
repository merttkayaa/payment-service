package com.grpc.paymentservice.config;

import io.grpc.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcServerRunner implements CommandLineRunner {

    private final Server grpcServer;

    public GrpcServerRunner(Server grpcServer) {
        this.grpcServer = grpcServer;
    }

    @Override
    public void run(String... args) throws Exception {
        grpcServer.start();
        System.out.println("gRPC server started on port 9091.");
//        grpcServer.awaitTermination();
    }
}