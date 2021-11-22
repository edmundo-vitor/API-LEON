package br.com.leon.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "tb_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;
  private String name;
  private Boolean active;

  @Value("${0}")
  private int restitutions;

  @ManyToOne
  private Plan plan;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @JsonIdentityReference(alwaysAsId = true)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
  private Set<Payment> payments = new HashSet<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<UserSchedule> schedules = new HashSet<>();
}
