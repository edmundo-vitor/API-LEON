package br.com.leon.dto;

import br.com.leon.model.Authentication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationInsertDTO extends AuthenticationDTO {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public AuthenticationInsertDTO(Authentication entity) {
        super(entity);
        this.password = entity.getPassword();
    }
    
    public Authentication toAuthentication() {
    	Authentication authentication = new Authentication();
    	authentication.setId(getId());
    	authentication.setEmail(getEmail());
    	authentication.setPassword(password);
    	//authentication.setRoles(getRoles());
    	return authentication;
    }
}
