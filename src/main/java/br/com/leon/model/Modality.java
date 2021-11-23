package br.com.leon.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_modality")
@EqualsAndHashCode(callSuper = true)
public class Modality extends BaseEntity implements Serializable {
  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
  )
  @JsonIdentityReference(alwaysAsId = true)
  @JoinTable(
    name = "tb_modality_plan",
    joinColumns = @JoinColumn(name = "modality_id"),
    inverseJoinColumns = @JoinColumn(name = "plan_id")
  )
  @ManyToMany(fetch = FetchType.LAZY)
  private List<Plan> plans = new ArrayList<>();

  @OneToMany(mappedBy = "modality", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Schedule> schedules = new HashSet<>();
}
