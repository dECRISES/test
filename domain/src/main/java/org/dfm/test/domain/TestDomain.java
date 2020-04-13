package org.dfm.test.domain;

import org.dfm.test.domain.model.TestInfo;
import org.dfm.test.domain.port.ObtainTest;
import org.dfm.test.domain.port.RequestTest;

public class TestDomain implements RequestTest {

  private ObtainTest obtainTest;

  public TestDomain() {
    this(new ObtainTest() {
    });
  }

  public TestDomain(ObtainTest obtainTest) {
    this.obtainTest = obtainTest;
  }

  @Override
  public TestInfo getTests() {
    return TestInfo.builder().tests(obtainTest.getAllTests()).build();
  }
}
