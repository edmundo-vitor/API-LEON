package br.com.leon.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
  @Column(updatable = false)
  private Date createdAt;

  private Date updatedAt;

  @PrePersist
  private void onPrePersist() {
    createdAt = updatedAt = new Date();
  }

  @PreUpdate
  private void onPreUpdate() {
    updatedAt = new Date();
  }
}
