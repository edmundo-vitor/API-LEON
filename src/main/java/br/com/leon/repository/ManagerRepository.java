package br.com.leon.repository;

import br.com.leon.model.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("SELECT DISTINCT obj FROM Manager obj WHERE obj.permission = UPPER(:permission)")
    Page<Manager> findAllByPermissionPaged(String permission, Pageable pageable);
}
