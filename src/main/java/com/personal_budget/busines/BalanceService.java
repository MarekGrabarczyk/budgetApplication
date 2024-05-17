package com.personal_budget.busines;

import com.personal_budget.infrastucture.database.repository.EarningJpaRepository;
import com.personal_budget.infrastucture.database.repository.ExpenseJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@AllArgsConstructor
public class BalanceService {
    private ExpenseJpaRepository expenseRepository;
    private EarningJpaRepository earningRepository;

    public BigDecimal budgetBalance(){
        BigDecimal totalEarnings = earningRepository.findTotalEarnings();
        BigDecimal totalExpenses = expenseRepository.findTotalExpenses();

        BigDecimal balance = totalEarnings.subtract(totalExpenses);

        return balance;
    }
}
