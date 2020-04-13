package org.dfm.test.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.dfm.test.domain.port.ObtainTest;
import org.dfm.test.repository.TestRepository;
import org.dfm.test.repository.dao.TestDao;

@Configuration
@EntityScan("org.dfm.test.repository.entity")
@EnableJpaRepositories("org.dfm.test.repository.dao")
public class JpaAdapterConfig {

  @Bean
  public ObtainTest getTestRepository(TestDao testDao) {
    return new TestRepository(testDao);
  }
}
