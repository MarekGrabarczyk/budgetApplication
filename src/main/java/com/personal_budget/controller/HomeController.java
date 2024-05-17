package com.personal_budget.controller;

import com.personal_budget.busines.BalanceService;
import com.personal_budget.infrastucture.database.repository.EarningJpaRepository;
import com.personal_budget.infrastucture.database.repository.ExpenseJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

@Controller
@AllArgsConstructor
public class HomeController {
    private ExpenseJpaRepository expenseRepository;
    private EarningJpaRepository earningRepository;

    private BalanceService balanceService;

    static final String HOME = "/";

    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage(Model model) {
        BigDecimal totalEarnings = earningRepository.findTotalEarnings();
        BigDecimal totalExpenses = expenseRepository.findTotalExpenses();
        BigDecimal balance = balanceService.budgetBalance();
        model.addAttribute("totalEarnings", totalEarnings);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("balance", balance);
        return "home";
    }


}
