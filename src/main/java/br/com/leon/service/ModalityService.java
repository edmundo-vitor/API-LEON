package br.com.leon.service;

import br.com.leon.dto.CreateModalityDTO;
import br.com.leon.dto.ModalityDTO;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Modality;
import br.com.leon.repository.ModalityRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModalityService {
  @Autowired
  private ModalityRepository modalityRepo;

  public List<ModalityDTO> findAll() {
    return modalityRepo
      .findAll()
      .stream()
      .map(ModalityDTO::new)
      .collect(Collectors.toList());
  }
  
  @Transactional(readOnly = true)
  public ModalityDTO findById(Long id) {
    Optional<Modality> modality = modalityRepo.findById(id);

    if (!modality.isPresent()) {
      throw new NotFoundException("Entity not found");
    }

    return new ModalityDTO(modality.get());
  }

  public ModalityDTO save(CreateModalityDTO dto) {
    Modality modality = modalityRepo.save(CreateModalityDTO.toModel(dto));
    
    return new ModalityDTO(modality);
  }

  public void delete(Long id) {
    boolean modalityExists = modalityRepo.existsById(id);

    if (!modalityExists) {
      throw new NotFoundException("Entity not found");
    }

    modalityRepo.deleteById(id);
  }
}
