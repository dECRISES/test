package org.dfm.test.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.dfm.test.domain.TestDomain;
import org.dfm.test.domain.port.ObtainTest;
import org.dfm.test.domain.port.RequestTest;
import org.dfm.test.repository.config.JpaAdapterConfig;

@Configuration
@Import(JpaAdapterConfig.class)
public class BootstrapConfig {

  @Bean
  public RequestTest getRequestTest(ObtainTest obtainTest) {
    return new TestDomain(obtainTest);
  }
}
