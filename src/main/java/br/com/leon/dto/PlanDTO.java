package br.com.leon.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.leon.model.Modality;
import br.com.leon.model.Plan;
import br.com.leon.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "modalities")
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Name cannot be empty")
    private String name;
	
	@NotNull(message = "Price cannot be empty")
    private Float price;
	
    private List<Modality> modalities = new ArrayList<>();
	private Set<User> users = new HashSet<>();
	
	public PlanDTO(Plan entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
		this.modalities = entity.getModalities();
		this.users = entity.getUsers();
	}
	
	public Plan toPlan() {
		Plan plan = new Plan();
		plan.setId(id);
		plan.setName(name);
		plan.setPrice(price);
		plan.setModalities(modalities);
		plan.setUsers(users);
		return plan;
	}
}
