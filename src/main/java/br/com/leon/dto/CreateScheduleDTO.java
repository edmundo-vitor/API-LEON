package br.com.leon.dto;

import br.com.leon.model.Branch;
import br.com.leon.model.Schedule;
import br.com.leon.model.Teacher;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateScheduleDTO {
  private Long branchId;

  private Long teacherId;

  private Date startDate;

  private Date endDate;

  private int maxUsers;

  public static Schedule toModel(CreateScheduleDTO dto) {
	Schedule schedule = new Schedule();
	Branch branch = new Branch();
	branch.setId(dto.getBranchId());
	Teacher teacher = new Teacher();
	teacher.setId(dto.getTeacherId());
	
	schedule.setBranch(branch);
	schedule.setTeacher(teacher);
	schedule.setStartDate(dto.getStartDate());
	schedule.setEndDate(dto.getEndDate());
	schedule.setMaxUsers(dto.getMaxUsers());
	
	return schedule;
  }
}
