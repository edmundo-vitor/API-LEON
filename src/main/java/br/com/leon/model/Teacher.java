package br.com.leon.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "tb_teacher")
public class Teacher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String address;

  private String telephone;
  
  @Setter(AccessLevel.NONE)
  private Date createdAt;

  @Setter(AccessLevel.NONE)
  private Date updatedAt;

  @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Schedule> schedules = new HashSet<>();
  
  @PrePersist
  private void onPrePersist() {
    createdAt = updatedAt = new Date();
  }

  @PreUpdate
  private void onPreUpdate() {
    updatedAt = new Date();
  }
}
