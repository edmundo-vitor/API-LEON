package br.com.leon.service;

import br.com.leon.dto.TeacherDTO;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Teacher;
import br.com.leon.repository.TeacherRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {
  @Autowired
  private TeacherRepository teacherRepo;

  public List<TeacherDTO> findAll(Optional<String> name) {
    List<Teacher> teachers;
    if (name.isPresent()) {
      teachers = teacherRepo.searchByNameLike(name.get());
    } else {
      teachers = teacherRepo.findAll();
    }
    return teachers.stream().map(TeacherDTO::new).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public TeacherDTO findById(Long id) {
    Optional<Teacher> teacher = teacherRepo.findById(id);

    if (!teacher.isPresent()) {
      throw new NotFoundException("Entity not found");
    }

    return new TeacherDTO(teacher.get());
  }

  @Transactional
  public TeacherDTO save(TeacherDTO dto) {
    Teacher newTeacher = teacherRepo.save(TeacherDTO.toModel(dto));

    return new TeacherDTO(newTeacher);
  }

  @Transactional
  public TeacherDTO update(Long id, TeacherDTO dto) {
    Optional<Teacher> teacher = teacherRepo.findById(id);

    if (!teacher.isPresent()) {
      throw new NotFoundException("Entity not found");
    }

    teacher.get().setName(dto.getName());
    teacher.get().setAddress(dto.getAddress());

    Teacher updatedTeacher = teacherRepo.save(teacher.get());

    return new TeacherDTO(updatedTeacher);
  }

  @Transactional
  public void delete(Long id) {
    boolean teacherExists = teacherRepo.existsById(id);

    if (!teacherExists) {
      throw new NotFoundException("Entity not found");
    }

    teacherRepo.deleteById(id);
  }
}
