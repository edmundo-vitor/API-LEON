package br.com.leon.repository;

import br.com.leon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByAuthenticationEmail(String email);
}
