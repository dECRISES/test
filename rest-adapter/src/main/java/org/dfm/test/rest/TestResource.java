package org.dfm.test.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.dfm.test.domain.model.TestInfo;
import org.dfm.test.domain.port.RequestTest;

@RestController
@RequestMapping("/api/v1/tests")
public class TestResource {

  private RequestTest requestTest;

  public TestResource(RequestTest requestTest) {
    this.requestTest = requestTest;
  }

  @GetMapping
  public ResponseEntity<TestInfo> getTests() {
    return ResponseEntity.ok(requestTest.getTests());
  }
}
