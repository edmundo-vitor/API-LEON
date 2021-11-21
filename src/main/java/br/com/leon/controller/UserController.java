package br.com.leon.controller;

import br.com.leon.model.Authentication;
import br.com.leon.model.Role;
import br.com.leon.model.User;
import br.com.leon.repository.RoleRepository;
import br.com.leon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

//TODO Controller criado para somente inserir e authenticar um usuário, controller ainda não padronizada
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(repository.findAll(pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        user.getAuthentication().setPassword(passwordEncoder.encode(user.getAuthentication().getPassword()));

        Authentication authentication = new Authentication();
        authentication.setEmail(user.getAuthentication().getEmail());
        authentication.setPassword(user.getAuthentication().getPassword());

        for (Role roleDto : user.getAuthentication().getRoles()) {
            Role role = roleRepository.getById(roleDto.getId());
            authentication.getRoles().add(role);
        }
        user.setAuthentication(authentication);

        user = repository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
