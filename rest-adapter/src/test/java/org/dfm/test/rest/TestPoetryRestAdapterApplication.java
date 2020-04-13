package org.dfm.test.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.dfm.test.domain.port.RequestTest;

@SpringBootApplication
@ComponentScan(basePackages = "org.dfm.test")
public class TestPoetryRestAdapterApplication {

  @MockBean
  private RequestTest requestTest;

  public static void main(String[] args) {
    SpringApplication.run(TestPoetryRestAdapterApplication.class, args);
  }
}
