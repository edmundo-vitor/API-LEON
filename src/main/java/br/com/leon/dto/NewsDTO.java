package br.com.leon.dto;

import br.com.leon.model.Manager;
import br.com.leon.model.News;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class NewsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @URL(message = "Image url cannot be valid")
    private String imageUrl;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @NotNull(message = "Manager cannot be null")
    private ManagerDTO manager;

    public NewsDTO(Long id, String title, String description, String imageUrl, Date date, ManagerDTO manager) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.manager = manager;
    }

    public NewsDTO(News entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.imageUrl = entity.getImageUrl();
        this.date = entity.getDate();
    }

    public NewsDTO(News entity, Manager manager) {
        this(entity);
        this.manager = new ManagerDTO(manager);
    }
}
