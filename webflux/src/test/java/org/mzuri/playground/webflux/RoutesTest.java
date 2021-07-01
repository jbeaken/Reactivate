package org.mzuri.playground.webflux;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mzuri.playground.webflux.sftp.SftpHandler;
import org.mzuri.playground.webflux.sftp.SftpRouter;
import org.mzuri.playground.webflux.sftp.SftpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SftpRouter.class, SftpHandler.class})
@WebFluxTest()
class RoutesTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private SftpService sftpService;

    private WebTestClient webTestClient;

    /**
     * It is a known limitation of @WebFluxTest - there is currently no consistent way to detect RouterFunction beans, like we do for @Controller classes.
     * You can use bindToApplicationContext to test the router function using WebTestClient
     * Please see https://github.com/spring-projects/spring-boot/issues/10683
     */
    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

//    @Test
//    @DisplayName("valid json request, return 200 with json body")
//    void testGetDocumentAcceptJson() {
//        Document document = new Document("{ article : \"About some science stuff\"}", null);
//
//        when(documentService.findBy(any(DocumentRequest.class))).thenReturn(Mono.just(document));
//
//        webTestClient.get()
//                .uri("/abstracts/scopus_id/123455")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(String.class)
//                .value(content -> {
//                            Assertions.assertThat(content).isEqualTo("{ article : \"About some science stuff\"}");
//                        }
//                );
//    }
//
//    @Test
//    @DisplayName("valid xml request, return 200 with xml body")
//    void testGetDocumentAcceptXml() {
//        Document document = new Document("<article>About some science stuff</article>", null);
//
//        when(documentService.findBy(any(DocumentRequest.class))).thenReturn(Mono.just(document));
//
//        webTestClient.get()
//                .uri("/abstracts/scopus_id/123455")
//                .accept(MediaType.APPLICATION_XML)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_XML)
//                .expectBody(String.class)
//                .value(content -> {
//                            Assertions.assertThat(content).isEqualTo("<article>About some science stuff</article>");
//                        }
//                );
//    }
//
//    @Test
//    @DisplayName("when path has invalid id type, return BadRequest")
//    void testGetDocumentInvalidIdType() {
//
//        when(documentService.findBy(any())).thenReturn(Mono.empty());
//
//        webTestClient.get()
//                .uri("/abstracts/unmapped/1")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isBadRequest()
//                .expectBody(String.class)
//                .value(body -> {
//                            Assertions.assertThat(body).isEqualTo("Id type is invalid");
//                        }
//                );
//    }
//
//    @Test
//    @DisplayName("when service throws AbstractNotFoundException, return 404 Not Found")
//    void testGetDocumentIdNotFound() {
//
//        when(documentService.findBy(any())).thenReturn(Mono.error(new AbstractNotFoundException("Cannot find abstract with  1")));
//
//        webTestClient.get()
//                .uri("/abstracts/scopus_id/123455")
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isNotFound()
//                .expectBody(String.class)
//                .value(body -> {
//                            Assertions.assertThat(body).isEqualTo("Cannot find abstract with  1");
//                        }
//                );
//    }

    @Test
    @DisplayName("test")
    void test() {

        Mockito.when(sftpService.upload()).thenReturn(Mono.just("hello"));
        
        

        webTestClient.get()
                .uri("/upload")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> {
                            Assertions.assertThat(body).isEqualTo("all-good");
                        }
                );

        Mockito.verify(sftpService, Mockito.times(1)).upload();
    }
}
