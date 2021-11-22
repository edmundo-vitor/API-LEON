package br.com.leon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tb_modality")
@Data
@EqualsAndHashCode(exclude = "plans", callSuper = true)
public class Modality extends BaseEntity implements Serializable {
	public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JoinTable(name = "tb_modality_plan", joinColumns = @JoinColumn(name = "modality_id"), inverseJoinColumns = @JoinColumn(name = "plan_id"))
	@ManyToMany(fetch = FetchType.EAGER)
    private List<Plan> plans = new ArrayList<>();
}