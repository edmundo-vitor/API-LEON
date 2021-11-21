package br.com.leon.controller;

import br.com.leon.dto.NewsDTO;
import br.com.leon.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService service;

    @GetMapping
    public ResponseEntity<Page<NewsDTO>> findAll(Pageable pageable) {
        Page<NewsDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable Long id) {
        NewsDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO dto) {
        NewsDTO newDto = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id, @Valid @RequestBody NewsDTO dto) {
        NewsDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
