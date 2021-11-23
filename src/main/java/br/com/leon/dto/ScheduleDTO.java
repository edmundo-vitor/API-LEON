package br.com.leon.dto;

import br.com.leon.model.Schedule;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {
  private Long id;

  private Date startDate;

  private Date endDate;

  private int maxUsers;

  public ScheduleDTO(Schedule schedule) {
    id = schedule.getId();
    startDate = schedule.getStartDate();
    endDate = schedule.getEndDate();
    maxUsers = schedule.getMaxUsers();
  }
}
