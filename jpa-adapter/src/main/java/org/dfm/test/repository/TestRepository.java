package org.dfm.test.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.dfm.test.domain.model.Test;
import org.dfm.test.domain.port.ObtainTest;
import org.dfm.test.repository.dao.TestDao;
import org.dfm.test.repository.entity.TestEntity;

public class TestRepository implements ObtainTest {

  private TestDao testDao;

  public TestRepository(TestDao testDao) {
    this.testDao = testDao;
  }

  @Override
  public List<Test> getAllTests() {
    return testDao.findAll().stream().map(TestEntity::toModel).collect(Collectors.toList());
  }
}
