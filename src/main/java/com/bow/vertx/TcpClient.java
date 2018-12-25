package com.bow.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

/**
 * @author vv
 * @since 2018/11/19
 */
public class TcpClient {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        NetClientOptions options = new NetClientOptions().setConnectTimeout(10000);
        options.setReconnectAttempts(10).setReconnectInterval(500);
        NetClient client = vertx.createNetClient(options);
        client.connect(4321, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Connected!");
                //用Socket操作数据
                NetSocket socket = res.result();
                socket.write("request data");
            } else {
                System.out.println("Failed to connect: " + res.cause().getMessage());
            }
        });
    }
}
