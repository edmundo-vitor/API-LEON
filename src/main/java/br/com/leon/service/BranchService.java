package br.com.leon.service;

import br.com.leon.dto.BranchDTO;
import br.com.leon.exceptions.DatabaseException;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Branch;
import br.com.leon.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository repository;

    @Transactional(readOnly = true)
    public Page<BranchDTO> findAllPaged(Pageable pageable) {
        Page<Branch> page = repository.findAll(pageable);
        return page.map(BranchDTO::new);
    }

    @Transactional(readOnly = true)
    public BranchDTO findById(Long id) {
        Optional<Branch> obj = repository.findById(id);
        Branch entity = obj.orElseThrow(() -> new NotFoundException("Entity not found"));
        return new BranchDTO(entity);
    }

    @Transactional
    public BranchDTO save(BranchDTO dto) {
        Branch entity = new Branch();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new BranchDTO(entity);
    }

    @Transactional
    public BranchDTO update(Long id, BranchDTO dto) {
        try {
            Branch entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new BranchDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Id " + id + " not found");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(BranchDTO dto, Branch entity) {

        entity.setName(dto.getName());
        entity.setState(dto.getState());
        entity.setRoad(dto.getRoad());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setCity(dto.getCity());
        entity.setDescription(dto.getDescription());
    }
}
