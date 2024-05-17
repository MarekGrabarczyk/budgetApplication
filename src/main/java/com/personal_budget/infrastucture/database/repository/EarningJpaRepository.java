package com.personal_budget.infrastucture.database.repository;

import com.personal_budget.infrastucture.database.entity.EarningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface EarningJpaRepository extends JpaRepository<EarningEntity, Integer> {

    @Query("SELECT SUM(earning.salary) FROM EarningEntity earning")

    BigDecimal findTotalEarnings();
}
