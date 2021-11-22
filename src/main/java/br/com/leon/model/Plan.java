package br.com.leon.model;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_plan")
@Data
@EqualsAndHashCode(exclude = "modalities", callSuper = true)
public class Plan extends BaseEntity implements Serializable {
	public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float price; 
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JoinTable(name = "tb_modality_plan", joinColumns = @JoinColumn(name = "plan_id"), inverseJoinColumns = @JoinColumn(name = "modality_id"))
	@ManyToMany(fetch = FetchType.LAZY)
    private List<Modality> modalities = new ArrayList<>();
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonIdentityReference(alwaysAsId = true)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plan", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();
}
