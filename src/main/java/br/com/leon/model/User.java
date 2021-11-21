package br.com.leon.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "tb_user")
@Data
public class User implements Serializable {
  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Boolean active;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "authentication_id", referencedColumnName = "id")
  private Authentication authentication;

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
