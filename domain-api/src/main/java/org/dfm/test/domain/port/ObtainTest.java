package org.dfm.test.domain.port;

import java.util.List;
import org.dfm.test.domain.model.Test;

public interface ObtainTest {

  default List<Test> getAllTests() {
    Test test = Test.builder().id(1L).description("If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)").build();
    return List.of(test);
  }
}
