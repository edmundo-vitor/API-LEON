package br.com.leon.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leon.dto.ModalityDTO;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Modality;
import br.com.leon.repository.ModalityRepository;

@Service
public class ModalityService {
	
	@Autowired
	ModalityRepository modalityRepo;
	
	@Transactional(readOnly = true)
    public Page<ModalityDTO> findAll(Pageable pageable) {
        Page<Modality> page = modalityRepo.findAll(pageable);
        return page.map(ModalityDTO::new);
    }
	
	@Transactional(readOnly = true)
    public ModalityDTO findById(Long id) {
        Optional<Modality> obj = modalityRepo.findById(id);
        Modality entity = obj.orElseThrow(() -> new NotFoundException("Entity not found"));
        return new ModalityDTO(entity);
    }
	
	@Transactional
	public ModalityDTO save(ModalityDTO dto) {
		Modality modality = dto.toModality();
		return new ModalityDTO(modalityRepo.save(modality));
	}
	
	@Transactional
	public ModalityDTO update(Long id, ModalityDTO dto) {
		
	}
	
}
