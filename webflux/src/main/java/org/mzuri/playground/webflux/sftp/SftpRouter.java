package org.mzuri.playground.webflux.sftp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SftpRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(SftpHandler sftpHandler) {
        return RouterFunctions.route()
                .path("/", builder -> builder.GET("/upload", sftpHandler::upload))
                .build();
    }
}
