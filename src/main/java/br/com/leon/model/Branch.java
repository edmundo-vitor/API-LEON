package br.com.leon.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_branch")
@Data
public class Branch implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;
    private String road;
    private String street_number;
    private String city;
    private String description;

    @ManyToMany(mappedBy = "branches")
    private List<Manager> managers = new ArrayList<>();
}
