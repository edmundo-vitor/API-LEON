package br.com.leon.service;


import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leon.dto.PlanDTO;
import br.com.leon.exceptions.DatabaseException;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Plan;
import br.com.leon.repository.PlanRepository;

@Service
public class PlanService {

	@Autowired
	private PlanRepository planRepo;
	
	@Transactional(readOnly = true)
	public Page<PlanDTO> findAllPaged(Pageable pageable) {
		Page<Plan> page = planRepo.findAll(pageable);
		return page.map(PlanDTO::new);
	}
	
	@Transactional(readOnly = true)
	public PlanDTO findById(Long id) {
		Optional<Plan> obj = planRepo.findById(id);
		Plan entity = obj.orElseThrow(() -> new NotFoundException("Entity not found"));
		return new PlanDTO(entity);
	}
	
	@Transactional
	public PlanDTO save(PlanDTO dto) {
		Plan plan = dto.toPlan();
		return new PlanDTO(planRepo.save(plan));
	}
	
	@Transactional
	public PlanDTO update(Long id, PlanDTO dto) {
		try {
            Plan entity = planRepo.getById(id);
            entity = dto.toPlan();
            entity.setId(id);
            return new PlanDTO(planRepo.save(entity));
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Id " + id + " not found");
        }
	}
	
	public void delete(Long id) {
        try {
            planRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
