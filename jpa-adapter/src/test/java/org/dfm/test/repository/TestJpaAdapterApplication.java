package org.dfm.test.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.dfm.test.domain.port.ObtainTest;
import org.dfm.test.repository.dao.TestDao;

@SpringBootApplication
public class TestJpaAdapterApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestJpaAdapterApplication.class, args);
  }

  @TestConfiguration
  static class TestJpaTestConfig {

    @Bean
    public ObtainTest getObtainTestRepository(TestDao testDao) {
      return new TestRepository(testDao);
    }
  }
}
