package org.mzuri.playground.webflux;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@SpringBootApplication
public class WebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

}

@Configuration
class DocumentRouter {

	@Bean
	public RouterFunction<ServerResponse> routes(SftpHandler sftpHandler) {
		return RouterFunctions.route()
				.path("/",
						builder -> builder.GET("/upload", sftpHandler::upload))
				.build();
	}
}

@Component
@RequiredArgsConstructor
@Slf4j
class SftpHandler {

	@Autowired
	private SftpService sftpService;

	public Mono<ServerResponse> upload(ServerRequest request) {

		sftpService.upload();

		return status(OK)
				.contentType(MediaType.TEXT_PLAIN)
				.bodyValue("all-good");

	}
}
