package br.com.leon.service;

import br.com.leon.dto.ScheduleDTO;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.repository.ModalityRepository;
import br.com.leon.repository.ScheduleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
  @Autowired
  private ScheduleRepository scheduleRepo;

  @Autowired
  private ModalityRepository modalityRepo;

  public List<ScheduleDTO> findByModalityId(Long id) {
    boolean modalityExists = modalityRepo.existsById(id);

    if (!modalityExists) {
      throw new NotFoundException("Entity not found");
    }

    return scheduleRepo
      .findByModalityId(id)
      .stream()
      .map(ScheduleDTO::new)
      .collect(Collectors.toList());
  }
  
  public void delete(Long id) {
	  boolean scheduleExists = scheduleRepo.existsById(id);
	  
	  if (!scheduleExists) {
		  throw new NotFoundException("Entity not found");
	  }
	  
	  scheduleRepo.deleteById(id);
  }
}
