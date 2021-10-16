package br.com.leon.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_manager")
@Data
public class Manager implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name cannot be empty")
    private String name;

    @Email(message = "invalid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;

    @NotBlank(message = "permission cannot be empty")
    private String permission;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_branch_manager",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    private List<Branch> branches = new ArrayList<>();
}
