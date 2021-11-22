package br.com.leon.service;

import br.com.leon.dto.NewsDTO;
import br.com.leon.exceptions.DatabaseException;
import br.com.leon.exceptions.NotFoundException;
import br.com.leon.model.Manager;
import br.com.leon.model.News;
import br.com.leon.repository.ManagerRepository;
import br.com.leon.repository.NewsRepository;
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
public class NewsService {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private ManagerRepository managerRepository;

    @Transactional(readOnly = true)
    public Page<NewsDTO> findAllPaged(Pageable pageable) {
        Page<News> page = repository.findAll(pageable);
        return page.map(NewsDTO::new);
    }

    @Transactional(readOnly = true)
    public NewsDTO findById(Long id) {
        Optional<News> obj = repository.findById(id);
        News entity = obj.orElseThrow(() -> new NotFoundException("Entity not found"));
        return new NewsDTO(entity);
    }

    @Transactional
    public NewsDTO save(NewsDTO dto) {
        News entity = new News();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new NewsDTO(entity);
    }

    @Transactional
    public NewsDTO update(Long id, NewsDTO dto) {
        try {
            News entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new NewsDTO(entity);
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

    private void copyDtoToEntity(NewsDTO dto, News entity) {

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImageUrl(dto.getImageUrl());
        entity.setDate(dto.getDate());

        Manager manager = managerRepository.getById(dto.getManager().getId());
        entity.setManager(manager);
    }
}
