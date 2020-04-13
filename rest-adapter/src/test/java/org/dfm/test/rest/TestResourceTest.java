package org.dfm.test.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.dfm.test.domain.model.Test;
import org.dfm.test.domain.model.TestInfo;
import org.dfm.test.domain.port.RequestTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = TestPoetryRestAdapterApplication.class, webEnvironment = RANDOM_PORT)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TestResourceTest {

  private static final String LOCALHOST = "http://localhost:";
  private static final String API_URI = "/api/v1/tests";
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private RequestTest requestTest;

  @Test
  @DisplayName("should start the rest adapter application")
  public void startup() {
    assertThat(Boolean.TRUE).isTrue();
  }

  @Test
  @DisplayName("should give tests when asked for tests with the support of domain stub")
  public void obtainTestsFromDomainStub() {
    // Given
    Test test = Test.builder().id(1L).description("Johnny Johnny Yes Papa !!").build();
    Mockito.lenient().when(requestTest.getTests()).thenReturn(TestInfo.builder().tests(List.of(test)).build());
    // When
    String url = LOCALHOST + port + API_URI;
    ResponseEntity<TestInfo> responseEntity = restTemplate.getForEntity(url, TestInfo.class);
    // Then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody().getTests()).isNotEmpty().extracting("description")
        .contains("Johnny Johnny Yes Papa !!");
  }
}
