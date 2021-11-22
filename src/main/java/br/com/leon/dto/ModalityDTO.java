package br.com.leon.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.leon.model.Modality;
import br.com.leon.model.Plan;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModalityDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Name cannot be empty")
	private String name;
	private List<Plan> plans;
	
	public ModalityDTO(Long id, @NotBlank(message = "Name cannot be empty") String name, List<Plan> plans) {
		this.id = id;
		this.name = name;
		this.plans = plans;
	}
	
	public ModalityDTO(Modality modality) {
		this.id = modality.getId();
		this.name = modality.getName();
		this.plans = modality.getPlans();
	}
	
	public Modality toModality() {
		Modality modality = new Modality();
		modality.setId(id);
		modality.setName(name);
		modality.setPlans(plans);
		return modality;
	}
}
