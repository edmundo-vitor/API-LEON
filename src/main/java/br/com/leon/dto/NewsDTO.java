package br.com.leon.dto;

import br.com.leon.model.Manager;
import br.com.leon.model.News;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class NewsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Date cannot be null")
    private Date date;

    @NotNull(message = "Manager cannot be null")
    private ManagerDTO manager;

    public NewsDTO(Long id, String description, Date date, ManagerDTO manager) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.manager = manager;
    }

    public NewsDTO(News entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.date = entity.getDate();
    }

    public NewsDTO(News entity, Manager manager) {
        this(entity);
        this.manager = new ManagerDTO(manager);
    }
}
