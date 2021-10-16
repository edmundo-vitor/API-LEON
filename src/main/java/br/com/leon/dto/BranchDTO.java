package br.com.leon.dto;

import br.com.leon.model.Branch;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BranchDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String state;
    private String road;
    private String street_number;
    private String city;
    private String description;

    public BranchDTO(Long id, String name, String state, String road, String street_number, String city, String description) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.road = road;
        this.street_number = street_number;
        this.city = city;
        this.description = description;
    }

    public BranchDTO(Branch entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.state = entity.getState();
        this.road = entity.getRoad();
        this.street_number = entity.getStreet_number();
        this.city = entity.getCity();
        this.description = entity.getDescription();
    }
}
