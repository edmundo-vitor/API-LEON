package br.com.leon.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_manager")
@Data
public class Manager extends BaseEntity implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authentication_id", referencedColumnName = "id")
    private Authentication authentication;

    @OneToMany(mappedBy = "manager")
    private List<News> news = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_branch_manager",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    private List<Branch> branches = new ArrayList<>();
}
