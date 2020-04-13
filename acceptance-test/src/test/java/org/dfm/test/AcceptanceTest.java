package org.dfm.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.dfm.test.domain.TestDomain;
import org.dfm.test.domain.model.Test;
import org.dfm.test.domain.model.TestInfo;
import org.dfm.test.domain.port.ObtainTest;
import org.dfm.test.domain.port.RequestTest;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AcceptanceTest {

  @Test
  @DisplayName("should be able to get tests when asked for tests from hard coded tests")
  public void getTestsFromHardCoded() {
  /*
      RequestTest    - left side port
      TestDomain     - hexagon (domain)
      ObtainTest     - right side port
   */
    RequestTest requestTest = new TestDomain(); // the poem is hard coded
    TestInfo testInfo = requestTest.getTests();
    assertThat(testInfo).isNotNull();
    assertThat(testInfo.getTests()).isNotEmpty().extracting("description")
        .contains("If you could read a leaf or tree\r\nyoud have no need of books.\r\n-- Alistair Cockburn (1987)");
  }

  @Test
  @DisplayName("should be able to get tests when asked for tests from stub")
  public void getTestsFromMockedStub(@Mock ObtainTest obtainTest) {
    // Stub
    Test test = Test.builder().id(1L).description("I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)").build();
    Mockito.lenient().when(obtainTest.getAllTests()).thenReturn(List.of(test));
    // hexagon
    RequestTest requestTest = new TestDomain(obtainTest);
    TestInfo testInfo = requestTest.getTests();
    assertThat(testInfo).isNotNull();
    assertThat(testInfo.getTests()).isNotEmpty().extracting("description")
        .contains("I want to sleep\r\nSwat the flies\r\nSoftly, please.\r\n\r\n-- Masaoka Shiki (1867-1902)");
  }
}
