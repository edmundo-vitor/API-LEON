package br.com.leon.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leon.dto.UserDTO;
import br.com.leon.exceptions.DatabaseException;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Authentication;
import br.com.leon.model.Role;
import br.com.leon.model.User;
import br.com.leon.repository.RoleRepository;
import br.com.leon.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable) {
		Page<User> page = userRepo.findAll(pageable);
		return page.map(UserDTO::new);
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = userRepo.findById(id);
		User entity = obj.orElseThrow(() -> new NotFoundException("Entity not found"));
		return new UserDTO(entity);
	}
	
	@Transactional
	public UserDTO save(UserDTO dto) {
		User user = dto.toUser();
		
		user.getAuthentication().setPassword(passwordEncoder.encode(user.getAuthentication().getPassword()));

        Authentication authentication = new Authentication();
        authentication.setEmail(user.getAuthentication().getEmail());
        authentication.setPassword(user.getAuthentication().getPassword());

        for (Role roleDto : user.getAuthentication().getRoles()) {
            Role role = roleRepository.getById(roleDto.getId());
            authentication.getRoles().add(role);
        }
        user.setAuthentication(authentication);

        return new UserDTO(userRepo.save(user));
	}
	
	@Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = userRepo.getById(id);
            entity = dto.toUser();
            entity.setId(id);
            entity.getAuthentication().setPassword(passwordEncoder.encode(entity.getAuthentication().getPassword()));
            entity = userRepo.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Id " + id + " not found");
        }
    }
	
	public void delete(Long id) {
        try {
        	userRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
