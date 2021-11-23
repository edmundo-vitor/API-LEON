package br.com.leon.dto;

import br.com.leon.model.Modality;
import br.com.leon.model.Schedule;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateModalityDTO {
  private String name;

  private List<CreateScheduleDTO> schedules;

  public static Modality toModel(CreateModalityDTO dto) {
    Modality modality = new Modality();
    modality.setName(dto.getName());
    modality
      .setSchedules(dto.getSchedules().stream().map(schedule -> {
    	  Schedule model = CreateScheduleDTO.toModel(schedule);
    	  model.setModality(modality);
    	  return model;
      })
      .collect(Collectors.toSet()));
    
    return modality;
  }
}
