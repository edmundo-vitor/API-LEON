package br.com.leon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_branch")
@Data
public class Branch extends BaseEntity implements Serializable {
  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String state;
  private String road;
  private Integer streetNumber;
  private String city;
  private String description;

  @ManyToMany(mappedBy = "branches")
  private List<Manager> managers = new ArrayList<>();

  @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Schedule> schedules = new HashSet<>();
}
