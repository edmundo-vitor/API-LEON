package br.com.leon.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(
  name = "tb_schedule",
  uniqueConstraints = @UniqueConstraint(
    columnNames = { "modality_id", "branch_id", "teacher_id" }
  )
)
public class Schedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Date startDate;

  @Column(nullable = false)
  private Date endDate;

  @Column(nullable = false)
  private int maxUsers;

  @Setter(AccessLevel.NONE)
  private Date createdAt;

  @Setter(AccessLevel.NONE)
  private Date updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", nullable = false)
  private Teacher teacher;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "branch_id", nullable = false)
  private Branch branch;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modality_id", nullable = false)
  private Modality modality;

  @PrePersist
  private void onPrePersist() {
    createdAt = updatedAt = new Date();
  }

  @PreUpdate
  private void onPreUpdate() {
    updatedAt = new Date();
  }
}
