package br.com.leon.dto;

import br.com.leon.model.Authentication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
}
