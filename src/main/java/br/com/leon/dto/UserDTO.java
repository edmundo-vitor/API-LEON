package br.com.leon.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.leon.model.Authentication;
import br.com.leon.model.Payment;
import br.com.leon.model.User;
import br.com.leon.model.UserSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authentication")
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Boolean active;
	private Authentication authentication;
    private int restitutions;
  	private PlanDTO plan;
  	private Set<Payment> payments = new HashSet<>();
  	private Set<UserSchedule> schedules = new HashSet<>();
  	
  	public UserDTO(User entity) {
  		this.id = entity.getId();
  		this.name = entity.getName();
  		this.active = entity.getActive();
  		this.authentication = entity.getAuthentication();
  		this.restitutions = entity.getRestitutions();
  		
  		if(entity.getPlan() != null)
  			this.plan = new PlanDTO(entity.getPlan());
  		
  		this.payments = entity.getPayments();
  		this.schedules = entity.getSchedules();
  	}
  	
  	public User toUser() {
  		User user = new User();
  		user.setId(id);
  		user.setName(name);
  		user.setActive(active);
  		user.setAuthentication(authentication);
  		user.setRestitutions(restitutions);
  		
  		if(plan != null)
  			user.setPlan(plan.toPlan());
  		
  		user.setPayments(payments);
  		user.setSchedules(schedules);
  		return user;
  	}
}
