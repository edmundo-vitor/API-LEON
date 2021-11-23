package br.com.leon.controller;

import br.com.leon.dto.CreateModalityDTO;
import br.com.leon.dto.ModalityDTO;
import br.com.leon.service.ModalityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/modalities")
public class ModalityController {
  @Autowired
  private ModalityService modalityServ;

  @GetMapping
  public List<ModalityDTO> findAll() {
    return modalityServ.findAll();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<ModalityDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(modalityServ.findById(id));
  }
  
  @PostMapping
  public ResponseEntity<ModalityDTO> save(@RequestBody CreateModalityDTO dto) {
	ModalityDTO result = modalityServ.save(dto);
	return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(Long id) {
    modalityServ.delete(id);
    return ResponseEntity.noContent().build();
  }
}
