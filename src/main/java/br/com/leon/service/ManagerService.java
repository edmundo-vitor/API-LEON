package br.com.leon.service;

import br.com.leon.dto.BranchDTO;
import br.com.leon.dto.ManagerDTO;
import br.com.leon.dto.ManagerInsertDTO;
import br.com.leon.exceptions.DatabaseException;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Branch;
import br.com.leon.model.Manager;
import br.com.leon.repository.BranchRepository;
import br.com.leon.repository.ManagerRepository;
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
public class ManagerService {

    @Autowired
    private ManagerRepository repository;

    @Autowired
    private BranchRepository branchRepository;

    @Transactional(readOnly = true)
    public Page<ManagerDTO> findAllByPermissionPaged(String permission, Pageable pageable) {
        Page<Manager> page = repository.findAllByPermissionPaged(permission, pageable);
        return page.map(ManagerDTO::new);
    }

    @Transactional(readOnly = true)
    public ManagerDTO findById(Long id) {
        Optional<Manager> obj = repository.findById(id);
        Manager entity = obj.orElseThrow(() -> new NotFoundException("Entity not found"));
        return new ManagerDTO(entity);
    }

    @Transactional
    public ManagerDTO save(ManagerInsertDTO dto) {
        Manager entity = new Manager();
        copyDtoToEntity(dto, entity);
        entity.setPassword(dto.getPassword());  //TODO criptografar senha quando usar o spring security
        entity = repository.save(entity);
        return new ManagerDTO(entity, entity.getBranches());
    }

    //TODO tratar a exception de email duplicado
    @Transactional
    public ManagerDTO update(Long id, ManagerDTO dto) {
        try {
            Manager entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ManagerDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new DatabaseException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ManagerDTO dto, Manager entity) {

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPermission(dto.getPermission());

        entity.getBranches().clear();
        for (BranchDTO branchDTO : dto.getBranches()) {
            Branch branch = branchRepository.getById(branchDTO.getId());
            entity.getBranches().add(branch);
        }
    }
}
