package br.com.leon.dto;

import br.com.leon.model.News;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class NewsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Date cannot be empty")
    private Date date;
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
        this.manager = new ManagerDTO(entity.getManager());
    }
}
