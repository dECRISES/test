package org.dfm.test.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.dfm.test.repository.entity.TestEntity;

@Repository
public interface TestDao extends JpaRepository<TestEntity, Long> {

}
