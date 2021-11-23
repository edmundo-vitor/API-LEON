package br.com.leon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.leon.dto.ScheduleDTO;
import br.com.leon.service.ScheduleService;

@RestController
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleServ;
	
	@GetMapping("/modalities/{id}/schedules")
	public List<ScheduleDTO> findByModalityId(@PathVariable Long id) {
		return scheduleServ.findByModalityId(id);
	}
	
	@DeleteMapping("/schedules/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		scheduleServ.delete(id);
		return ResponseEntity.noContent().build();
	}
}

