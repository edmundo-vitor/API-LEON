package br.com.leon.controller;

import br.com.leon.dto.ManagerDTO;
import br.com.leon.dto.ManagerInsertDTO;
import br.com.leon.model.Role;
import br.com.leon.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/managers")
public class ManagerController {

    @Autowired
    private ManagerService service;

    @GetMapping
    public ResponseEntity<Page<ManagerDTO>> findAll(Pageable pageable) {
        Page<ManagerDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> findAllRoles() {
        List<Role> roles = service.findAllRoles();
        return ResponseEntity.ok().body(roles);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ManagerDTO> findById(@PathVariable Long id) {
        ManagerDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ManagerDTO> save(@Valid @RequestBody ManagerInsertDTO dto) {
        ManagerDTO newDto = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerDTO> update(@PathVariable Long id, @Valid @RequestBody ManagerInsertDTO dto) {
        ManagerDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
