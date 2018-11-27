package com.alkimii.test.testactioncable;

import com.hosopy.actioncable.ActionCable;
import com.hosopy.actioncable.Channel;
import com.hosopy.actioncable.Consumer;
import com.hosopy.actioncable.Subscription;
import com.squareup.okhttp.OkHttpClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class TestActionCable {

    Subscription subscription;
    Consumer consumer;
    Channel RoomChannel;





    public void create() throws URISyntaxException {

        URI uri;

        uri = new URI("wss://comms.alkimii.com/cable");
        final Consumer.Options options = new Consumer.Options();
        Map<String, String> headers = new HashMap();
        headers.put("x-access-token", "_0Fl_GIBATXwKtH0O1e8");
        headers.put("x-user-token","EQtx5GYBTK3hFfKY2-e3");
        headers.put("Origin", "https://comms.alkimii.com/cable");
        options.headers = headers;
        options.reconnection = true;
        options.okHttpClientFactory = () -> {
            final OkHttpClient client = new OkHttpClient();
            client.setRetryOnConnectionFailure(true);

            client.networkInterceptors().add(chain -> null);
            return client;
        };
        consumer = ActionCable.createConsumer(uri,options);
        RoomChannel = new Channel("RoomChannel");
        RoomChannel.addParam("user_token","EQtx5GYBTK3hFfKY2-e3");
        RoomChannel.addParam("autoSubscribe",true);
        RoomChannel.addParam("bufferActions",true);
        subscription = consumer.getSubscriptions().create(RoomChannel);
        subscription.onConnected(() -> {
                    System.out.println("connected");




                }

        );

        subscription.onReceived(jsonElement -> {
            System.out.println("received "+jsonElement.toString());





        });


        subscription.onFailed(e -> {

            e.printStackTrace();

            System.out.println("failed " + e.getMessage());


        });

        subscription.onRejected(() -> System.out.println("rejected"));

        subscription.onDisconnected(() -> System.out.println("disconnected"));


consumer.connect();






    }

}
