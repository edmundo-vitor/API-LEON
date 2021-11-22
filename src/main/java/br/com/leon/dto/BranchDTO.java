package br.com.leon.dto;

import br.com.leon.model.Branch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class BranchDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "State cannot be empty")
    private String state;

    @NotBlank(message = "Road cannot be empty")
    private String road;

    @NotNull(message = "Street number cannot be null")
    private Integer streetNumber;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    public BranchDTO(Long id, String name, String state, String road, Integer streetNumber, String city, String description) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.road = road;
        this.streetNumber = streetNumber;
        this.city = city;
        this.description = description;
    }

    public BranchDTO(Branch entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.state = entity.getState();
        this.road = entity.getRoad();
        this.streetNumber = entity.getStreetNumber();
        this.city = entity.getCity();
        this.description = entity.getDescription();
    }
}
