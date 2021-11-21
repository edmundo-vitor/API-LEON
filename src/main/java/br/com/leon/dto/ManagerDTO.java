package br.com.leon.dto;

import br.com.leon.model.Authentication;
import br.com.leon.model.Branch;
import br.com.leon.model.Manager;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ManagerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    private List<BranchDTO> branches = new ArrayList<>();

    public ManagerDTO(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public ManagerDTO(Manager entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
    }

    public ManagerDTO(Manager entity, List<Branch> branches) {
        this(entity);
        branches.forEach(branch -> this.branches.add(new BranchDTO(branch)));
    }

    public ManagerDTO(Manager entity, List<Branch> branches, Authentication authentication) {
        this(entity);
        branches.forEach(branch -> this.branches.add(new BranchDTO(branch)));
    }
}
