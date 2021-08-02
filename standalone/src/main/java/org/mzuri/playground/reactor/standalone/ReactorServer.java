package org.mzuri.playground.reactor.standalone;

import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;


public class ReactorServer {

    public static void main(String[] args) throws InterruptedException {

        HttpServer server = HttpServer.create()   // Prepares an HTTP server ready for configuration
                .port(8080)    // Configures the port number as zero, this will let the system pick up
                .wiretap(true)
                .route(routes ->
                        // The server will respond only on POST requests
                        // where the path starts with /test and then there is path parameter
                        routes.post("/test/{param}", (request, response) ->
                                response.sendString(request.receive()
                                        .asString()
                                        .map(s -> s + ' ' + request.param("param") + '!')
                                        .log("http-server"))));


        DisposableServer disposableServer = server.bindNow();

        disposableServer.onDispose().block();
    }
}
