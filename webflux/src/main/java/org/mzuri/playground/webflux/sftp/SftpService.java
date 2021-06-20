package org.mzuri.playground.webflux.sftp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.*;

@Component
@Slf4j
public class SftpService {


    public Mono<String> upload()  {

        SftpSession session = getSession();
        InputStream inputStream = new ByteArrayInputStream("hello".getBytes());

        try {
            session.write(inputStream, "/upload/hello");
        } catch (IOException e) {
            log.error("Error", e);
            return Mono.error(() -> new RuntimeException("not good"));
        }

        return Mono.just( "all-good" );

    }

    SftpSession getSession() {

        var sftpSessionFactory = new DefaultSftpSessionFactory();

        sftpSessionFactory.setHost("localhost");
        sftpSessionFactory.setPort(22);
        sftpSessionFactory.setAllowUnknownKeys(true);
        sftpSessionFactory.setUser("foo");
        sftpSessionFactory.setPassword("pass");

        return sftpSessionFactory.getSession();
    }
}
