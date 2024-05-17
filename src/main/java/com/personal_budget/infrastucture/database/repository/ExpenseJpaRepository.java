package com.personal_budget.infrastucture.database.repository;

import com.personal_budget.infrastucture.database.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ExpenseJpaRepository extends JpaRepository<ExpenseEntity, Integer> {

    @Query("SELECT SUM(expense.price) FROM ExpenseEntity expense")
    BigDecimal findTotalExpenses();
}
