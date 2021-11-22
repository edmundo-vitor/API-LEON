package br.com.leon.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leon.dto.ModalityDTO;
import br.com.leon.service.ModalityService;

@RestController
@RequestMapping(value = "/modalities")
public class ModalityController {
	
	@Autowired
	ModalityService modalityService;
	
	@GetMapping
	public ResponseEntity<Page<ModalityDTO>> findAll(Pageable pageable) {
		Page<ModalityDTO> list = modalityService.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ModalityDTO> findById(@PathVariable Long id) {
		ModalityDTO dto = modalityService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ModalityDTO> save(@Valid @RequestBody ModalityDTO dto) {
		ModalityDTO newDto = modalityService.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
}
