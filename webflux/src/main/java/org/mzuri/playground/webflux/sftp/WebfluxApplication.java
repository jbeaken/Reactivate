package org.mzuri.playground.webflux.sftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class WebfluxApplication {

	public static void main(String[] args) {
		BlockHound.install();
		SpringApplication.run(WebfluxApplication.class, args);
	}
}

