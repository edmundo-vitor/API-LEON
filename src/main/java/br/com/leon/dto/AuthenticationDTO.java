package br.com.leon.dto;

import br.com.leon.model.Authentication;
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
public class AuthenticationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true)
    private String email;

    Set<RoleDTO> roles = new HashSet<>();

    public AuthenticationDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public AuthenticationDTO(Authentication entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
