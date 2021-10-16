package br.com.leon.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_branch")
@Data
public class Branch implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "state cannot be empty")
    private String state;

    @NotBlank(message = "road cannot be empty")
    private String road;

    @NotBlank(message = "street number cannot be empty")
    private String street_number;

    @NotBlank(message = "city cannot be empty")
    private String city;

    @NotBlank(message = "description cannot be empty")
    private String description;

    @ManyToMany(mappedBy = "branches")
    private List<Manager> managers = new ArrayList<>();
}
