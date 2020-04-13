package org.dfm.test.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.dfm.test.domain.model.Test;
import org.dfm.test.domain.port.ObtainTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TestJpaTest {

  @Autowired
  private ObtainTest obtainTest;

  @Test
  @DisplayName("should start the application")
  public void startup() {
    assertThat(Boolean.TRUE).isTrue();
  }

  @Sql(scripts = {"/sql/data.sql"})
  @Test
  @DisplayName("should give me tests when asked for tests from database")
  public void shouldGiveMeTestsWhenAskedForTests() {
    // Given from @Sql
    // When
    List<Test> tests = obtainTest.getAllTests();
    // Then
    assertThat(tests).isNotNull().extracting("description").contains("Twinkle twinkle little star");
  }
}
