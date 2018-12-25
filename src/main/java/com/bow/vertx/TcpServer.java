package com.bow.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

/**
 * 展示vertx的使用
 *
 * @author vv
 * @since 2018/11/19
 */
public class TcpServer {

	private NetServer server;

	public void start(){
        // vertx设置
        VertxOptions options = new VertxOptions();
        options.setWorkerPoolSize(20);
        Vertx vertx = Vertx.vertx();

        // server设置
        NetServerOptions serverOptions = new NetServerOptions().setPort(4321);
        server = vertx.createNetServer(serverOptions);

        // 请求处理的handler
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                System.out.println("Received some bytes: " + buffer.length());
                Buffer buf = Buffer.buffer().appendFloat(12.34f).appendInt(123);
                socket.write(buf);
                // Write a string in UTF-8 encoding
                socket.write("response data");
            });
        }).connectHandler(socket -> {
            // If you want to be notified when a socket is closed
            socket.closeHandler(v -> {
                System.out.println("The socket has been closed");
            });
        });

        server.listen();
        System.out.println("Tcp server started.");
    }
	public void close() {
		server.close(res -> {
			if (res.succeeded()) {
				System.out.println("Server is now closed");
			} else {
				System.out.println("close failed");
			}
		});
	}

    public static void main(String[] args) throws Exception {
        TcpServer tcpServer = new TcpServer();
        tcpServer.start();
    }
}
