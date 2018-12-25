package com.bow.vertx;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;

/**
 * @author vv
 * @since 2018/11/19
 */
public class RestServer {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		HttpServerOptions options = new HttpServerOptions().setMaxWebsocketFrameSize(1000000);
		HttpServer server = vertx.createHttpServer(options);

		// 设置request handler
		server.requestHandler(request -> {
			// 可以通过response拿到输出流
			HttpServerResponse response = request.response();
			response.end("Hello world");
		});

		// 绑定端口
		server.listen(8080, "localhost", res -> {
			if (res.succeeded()) {
				System.out.println("Server is now listening!");
			} else {
				System.out.println("Failed to bind!");
			}
		});
	}
}
