package br.com.leon.controller;

import br.com.leon.dto.TeacherDTO;
import br.com.leon.service.TeacherService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherController {
  @Autowired
  private TeacherService teacherService;

  @GetMapping
  public List<TeacherDTO> findAll() {
    return teacherService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeacherDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(teacherService.findById(id));
  }

  @PostMapping
  public ResponseEntity<TeacherDTO> save(@Valid @RequestBody TeacherDTO dto) {
    return ResponseEntity.ok(teacherService.save(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TeacherDTO> update(
    @PathVariable Long id,
    @Valid @RequestBody TeacherDTO dto
  ) {
    return ResponseEntity.ok(teacherService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    teacherService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
