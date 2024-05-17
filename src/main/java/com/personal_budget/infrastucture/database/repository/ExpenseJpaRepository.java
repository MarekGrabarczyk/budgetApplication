package com.personal_budget.infrastucture.database.repository;

import com.personal_budget.infrastucture.database.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseJpaRepository extends JpaRepository<ExpenseEntity, Integer> {
}
