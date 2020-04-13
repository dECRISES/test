package org.dfm.test.repository.entity;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dfm.test.domain.model.Test;

@Table(name = "T_TEST")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestEntity {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  @Column(name = "DESCRIPTION")
  private String description;

  public Test toModel() {
    return Test.builder().id(id).description(description).build();
  }
}
