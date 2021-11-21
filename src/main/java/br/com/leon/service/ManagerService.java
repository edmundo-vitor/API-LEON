package br.com.leon.service;

import br.com.leon.dto.BranchDTO;
import br.com.leon.dto.ManagerDTO;
import br.com.leon.dto.ManagerInsertDTO;
import br.com.leon.dto.RoleDTO;
import br.com.leon.exceptions.DatabaseException;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Authentication;
import br.com.leon.model.Branch;
import br.com.leon.model.Manager;
import br.com.leon.model.Role;
import br.com.leon.repository.BranchRepository;
import br.com.leon.repository.ManagerRepository;
import br.com.leon.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository repository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<ManagerDTO> findAllPaged(Pageable pageable) {
        Page<Manager> page = repository.findAll(pageable);
        return page.map(ManagerDTO::new);
    }

    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ManagerDTO findById(Long id) {
        Optional<Manager> obj = repository.findById(id);
        Manager entity = obj.orElseThrow(() -> new NotFoundException("Entity not found"));
        return new ManagerInsertDTO(entity, entity.getBranches(), entity.getAuthentication());
    }

    @Transactional
    public ManagerDTO save(ManagerInsertDTO dto) {
        Manager entity = new Manager();
        copyDtoToEntity(dto, entity);
        entity.getAuthentication().setPassword(passwordEncoder.encode(dto.getAuthentication().getPassword()));
        entity = repository.save(entity);
        return new ManagerDTO(entity, entity.getBranches());
    }

    @Transactional
    public ManagerDTO update(Long id, ManagerInsertDTO dto) {
        try {
            Manager entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ManagerDTO(entity);
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

    private void copyDtoToEntity(ManagerInsertDTO dto, Manager entity) {

        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());

        Authentication authentication = new Authentication();
        authentication.setEmail(dto.getAuthentication().getEmail());

        for (RoleDTO roleDto : dto.getAuthentication().getRoles()) {
            Role role = roleRepository.getById(roleDto.getId());
            authentication.getRoles().add(role);
        }
        entity.setAuthentication(authentication);

        entity.getBranches().clear();
        for (BranchDTO branchDTO : dto.getBranches()) {
            Branch branch = branchRepository.getById(branchDTO.getId());
            entity.getBranches().add(branch);
        }
    }
}
