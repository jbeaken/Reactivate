package org.mzuri.playground.webflux.sftp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
@RequiredArgsConstructor
@Slf4j
public class SftpHandler {

    @Autowired
    private SftpService sftpService;

    public Mono<ServerResponse> upload(ServerRequest request) {

        Mono<String> upload = sftpService.upload();

        return status(OK)
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("all-good");

    }
}
