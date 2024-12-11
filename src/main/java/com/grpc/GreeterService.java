package com.grpc;

import com.grpc.Config.HelloRequest;
import com.grpc.Config.HelloReply;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public class GreeterService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        System.out.println("Request Key Data this "+ request.getKeyData());
        ServerCallStreamObserver clientCallStreamObserver=(ServerCallStreamObserver)responseObserver;
        clientCallStreamObserver.setOnReadyHandler(()-> {
            if(clientCallStreamObserver.isReady()){
                HelloReply hr = HelloReply.newBuilder().setMessage("KeyData ="+request.getKeyData()).build();
                 responseObserver.onNext(hr);
                 responseObserver.onCompleted();
            }
        });

    }

}
