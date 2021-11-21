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

  public List<TeacherDTO> findAll() {
    return teacherRepo
      .findAll()
      .stream()
      .map(TeacherDTO::new)
      .collect(Collectors.toList());
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
  public void delete(Long id) {
    boolean teacherExists = teacherRepo.existsById(id);
    
    if (!teacherExists) {
    	throw new NotFoundException("Entity not found");
    }
    
    teacherRepo.deleteById(id);
  }
}
