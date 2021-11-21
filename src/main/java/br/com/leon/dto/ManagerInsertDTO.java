package br.com.leon.dto;

import br.com.leon.model.Authentication;
import br.com.leon.model.Branch;
import br.com.leon.model.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerInsertDTO extends ManagerDTO {
    private static final long serialVersionUID = 1L;

    private AuthenticationInsertDTO authentication;

    public ManagerInsertDTO(Manager entity, List<Branch> branches, Authentication authentication) {
        super(entity, branches);
        this.authentication = new AuthenticationInsertDTO(authentication);
    }
}
