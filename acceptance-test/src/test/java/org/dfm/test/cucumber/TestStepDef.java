package org.dfm.test.cucumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.cucumber.java8.HookNoArgsBody;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.dfm.test.TestE2EApplication;
import org.dfm.test.domain.model.Test;
import org.dfm.test.domain.model.TestInfo;
import org.dfm.test.repository.dao.TestDao;
import org.dfm.test.repository.entity.TestEntity;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestE2EApplication.class, webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = TestE2EApplication.class, loader = SpringBootContextLoader.class)
public class TestStepDef implements En {

  private static final String LOCALHOST = "http://localhost:";
  private static final String API_URI = "/api/v1/tests";
  @LocalServerPort
  private int port;
  private ResponseEntity<TestInfo> responseEntity;

  public TestStepDef(TestRestTemplate restTemplate, TestDao poetryDao) {

    DataTableType((Map<String, String> row) -> Test.builder().description(row.get("description")).build());
    DataTableType((Map<String, String> row) -> TestEntity.builder().description(row.get("description")).build());

    Before((HookNoArgsBody) poetryDao::deleteAll);
    After((HookNoArgsBody) poetryDao::deleteAll);

    Given("the following tests exists in the library", (DataTable dataTable) -> {
      List<TestEntity> poems = dataTable.asList(TestEntity.class);
      poetryDao.saveAll(poems);
    });

    When("user requests for all tests", () -> {
      String url = LOCALHOST + port + API_URI;
      responseEntity = restTemplate.getForEntity(url, TestInfo.class);
    });

    Then("the user gets the following tests", (DataTable dataTable) -> {
      List<Test> expectedTestInfo = dataTable.asList(Test.class);
      assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(responseEntity.getBody()).isNotNull();
      assertThat(responseEntity.getBody().getTests()).isNotEmpty().extracting("description")
          .contains(expectedTestInfo.stream().map(Test::getDescription).toArray());
    });
  }
}


