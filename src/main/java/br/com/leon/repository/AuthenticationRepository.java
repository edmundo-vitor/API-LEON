package br.com.leon.repository;

import br.com.leon.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Authentication findByEmail(String email);
}
