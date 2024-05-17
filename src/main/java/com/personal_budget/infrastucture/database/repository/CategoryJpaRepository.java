package com.personal_budget.infrastucture.database.repository;

import com.personal_budget.infrastucture.database.entity.CategoryEntity;
import com.personal_budget.infrastucture.database.entity.EarnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {
}
