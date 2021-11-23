package br.com.leon.repository;

import br.com.leon.model.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  @Query("select s from Schedule s where s.modality.id = :id")
  List<Schedule> findByModalityId(@Param("id") Long id);
}
