package br.com.leon.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leon.dto.PlanDTO;
import br.com.leon.service.PlanService;

@RestController
@RequestMapping(value = "/plans")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@GetMapping
	public ResponseEntity<Page<PlanDTO>> findAll(Pageable pageable) {
		Page<PlanDTO> list = planService.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(planService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<PlanDTO> save(@Valid @RequestBody PlanDTO dto) {
		PlanDTO newDTO = planService.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PlanDTO> update(@PathVariable Long id, @Valid @RequestBody PlanDTO dto) {
		return ResponseEntity.ok().body(planService.update(id, dto));
	}
	
	@DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
