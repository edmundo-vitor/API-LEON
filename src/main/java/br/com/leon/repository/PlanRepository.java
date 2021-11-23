package br.com.leon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leon.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

}
