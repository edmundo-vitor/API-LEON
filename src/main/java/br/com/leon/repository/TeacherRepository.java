package br.com.leon.repository;

import br.com.leon.model.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  @Query("SELECT t FROM Teacher t WHERE t.name LIKE %:name%")
  List<Teacher> searchByNameLike(@Param("name") String name);
}
