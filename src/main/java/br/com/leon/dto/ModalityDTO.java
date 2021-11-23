package br.com.leon.dto;

import java.util.Date;

import br.com.leon.model.Modality;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModalityDTO {
  private Long id;

  private String name;
  
  private Date createdAt;
  
  private Date updatedAt;

  public ModalityDTO(Modality modality) {
    id = modality.getId();
    name = modality.getName();
    createdAt = modality.getCreatedAt();
    updatedAt = modality.getUpdatedAt();
  }

  public static Modality toModel(ModalityDTO dto) {
    Modality modality = new Modality();
    modality.setId(dto.getId());
    modality.setName(dto.getName());

    return modality;
  }
}
