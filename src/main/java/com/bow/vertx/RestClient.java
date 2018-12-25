package com.bow.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

/**
 * @author vv
 * @since 2018/11/19
 */
public class RestClient {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpClientOptions options = new HttpClientOptions().setKeepAlive(false);
        HttpClient client = vertx.createHttpClient(options);
        client.getNow(8080,"localhost", "/", response -> {
            response.bodyHandler(System.out::println);
        });
    }
}
