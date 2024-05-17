package com.personal_budget.controller;

import com.personal_budget.infrastucture.database.entity.EarnerEntity;
import com.personal_budget.infrastucture.database.entity.ExpenseEntity;
import com.personal_budget.infrastucture.database.repository.EarnerJpaRepository;
import com.personal_budget.infrastucture.database.repository.ExpenseJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private ExpenseJpaRepository expenseRepository;
    private EarnerJpaRepository earnerRepository;

    static final String HOME = "/";

    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage(Model model) {
        BigDecimal totalExpenses = expenseRepository.findTotalExpenses();
        model.addAttribute("totalExpenses", totalExpenses);
        return "home";
    }


}
