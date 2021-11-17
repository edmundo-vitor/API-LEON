package br.com.leon.dto;

import br.com.leon.model.Branch;
import br.com.leon.model.Manager;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ManagerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @NotBlank(message = "Permission cannot be empty")
    private String permission;

    private List<BranchDTO> branches = new ArrayList<>();

    public ManagerDTO(Long id, String name, String email, String address, String phone, String permission) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.permission = permission.toUpperCase();
    }

    public ManagerDTO(Manager entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
        this.permission = entity.getPermission().toUpperCase();
    }

    public ManagerDTO(Manager entity, List<Branch> branches) {
        this(entity);
        branches.forEach(branch -> this.branches.add(new BranchDTO(branch)));
    }
}
