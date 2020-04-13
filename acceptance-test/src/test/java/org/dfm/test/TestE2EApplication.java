package org.dfm.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.dfm.test.domain.TestDomain;
import org.dfm.test.domain.port.ObtainTest;
import org.dfm.test.domain.port.RequestTest;
import org.dfm.test.repository.config.JpaAdapterConfig;

@SpringBootApplication
public class TestE2EApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestE2EApplication.class);
  }

  @TestConfiguration
  @Import(JpaAdapterConfig.class)
  static class TestConfig {

    @Bean
    public RequestTest getRequestTest(final ObtainTest obtainTest) {
      return new TestDomain(obtainTest);
    }
  }
}
