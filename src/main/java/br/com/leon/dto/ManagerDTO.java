package br.com.leon.dto;

import br.com.leon.model.Branch;
import br.com.leon.model.Manager;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ManagerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String permission;
    private List<BranchDTO> branches = new ArrayList<>();

    public ManagerDTO(Long id, String name, String email, String permission) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.permission = permission;
    }

    public ManagerDTO(Manager entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.permission = entity.getPermission();
    }

    public ManagerDTO(Manager entity, List<Branch> branches) {
        this(entity);
        branches.forEach(branch -> this.branches.add(new BranchDTO(branch)));
    }
}
