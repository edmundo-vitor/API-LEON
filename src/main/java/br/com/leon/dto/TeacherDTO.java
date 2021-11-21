package br.com.leon.dto;

import br.com.leon.model.Teacher;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherDTO {
  private Long id;

  @NotBlank(message = "Name cannot be empty")
  private String name;

  @NotBlank(message = "Address cannot be empty")
  private String address;

  @Pattern(
    regexp = "^\\s*(\\d{2}|\\d{0})[-. ]?(\\d{5}|\\d{4})[-. ]?(\\d{4})[-. ]?\\s*$",
    message = "Invalid phone number"
  )
  private String telephone;
  
  private Date createdAt;
  
  private Date updatedAt;

  public TeacherDTO(Teacher teacher) {
    id = teacher.getId();
    name = teacher.getName();
    address = teacher.getAddress();
    telephone = teacher.getTelephone();
    createdAt = teacher.getCreatedAt();
    updatedAt = teacher.getUpdatedAt();
  }

  public static Teacher toModel(TeacherDTO teacherDto) {
    Teacher teacher = new Teacher();
    teacher.setAddress(teacherDto.getAddress());
    teacher.setName(teacherDto.getName());
    teacher.setTelephone(teacherDto.getTelephone());

    return teacher;
  }
}
