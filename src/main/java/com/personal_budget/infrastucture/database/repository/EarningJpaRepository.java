package com.personal_budget.infrastucture.database.repository;

import com.personal_budget.infrastucture.database.entity.EarningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EarningJpaRepository extends JpaRepository<EarningEntity, Integer> {
}
