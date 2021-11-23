package br.com.leon.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "tb_user")
@Data
@EqualsAndHashCode(exclude = {"authentication", "plan", "payments", "schedules"} , callSuper = true)
@ToString(exclude = {"authentication", "plan", "payments", "schedules"})
public class User extends BaseEntity implements Serializable {
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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @LazyCollection(LazyCollectionOption.FALSE)
  private Set<Payment> payments = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private Set<UserSchedule> schedules = new HashSet<>();
}
