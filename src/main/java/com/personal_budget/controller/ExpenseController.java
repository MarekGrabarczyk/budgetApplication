package com.personal_budget.controller;

import com.personal_budget.infrastucture.database.entity.CategoryEntity;
import com.personal_budget.infrastucture.database.entity.EarnerEntity;
import com.personal_budget.infrastucture.database.entity.ExpenseEntity;
import com.personal_budget.infrastucture.database.repository.CategoryJpaRepository;
import com.personal_budget.infrastucture.database.repository.EarnerJpaRepository;
import com.personal_budget.infrastucture.database.repository.ExpenseJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseJpaRepository expenseRepository;
    private EarnerJpaRepository earnerRepository;
    private CategoryJpaRepository categoryRepository;

    @GetMapping
    public String expensesList(Model model) {
        List<ExpenseEntity> allExpenses = expenseRepository.findAll();
        List<EarnerEntity> allEarners = earnerRepository.findAll();
        List<CategoryEntity> allCategories = categoryRepository.findAll();

        model.addAttribute("expenses", allExpenses);
        model.addAttribute("expenseDTO", new ExpenseDTO());
        model.addAttribute("earnerDTO", new EarnerDTO());
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("earners", allEarners);
        model.addAttribute("categories", allCategories);


        return "expense";
    }

    @PostMapping("/add")
    public String addExpense(
            @ModelAttribute("expenseDTO") ExpenseDTO expenseDTO,
            @ModelAttribute("earnerDTO") EarnerDTO earnerDTO,
            @ModelAttribute("categoryDTO") CategoryDTO categoryDTO
    ) {


        ExpenseEntity newExpense = ExpenseEntity.builder()
                .name(expenseDTO.getName())
                .price(expenseDTO.getPrice())
                .category(
                        CategoryEntity.builder()
                            .categoryId(categoryDTO.getCategoryId())
                            .name(categoryDTO.getName())
                            .build())
                .earner(
                        EarnerEntity.builder()
                                .earnerId(earnerDTO.getEarnerId())
                                .name(earnerDTO.getName())
                                .surname(earnerDTO.getSurname())
                                .email(earnerDTO.getEmail())
                                .build()
                )
                .build();


        expenseRepository.save(newExpense);

        return "redirect:/expense";
    }
    @PutMapping("/update")
    public String updateEarner(
            @ModelAttribute("expenseDTO") ExpenseDTO expenseDTO,
            @ModelAttribute("earnerDTO") EarnerDTO earnerDTO,
            @ModelAttribute("categoryDTO") CategoryDTO categoryDTO
    ) {
        ExpenseEntity existingExpense = expenseRepository.findById(expenseDTO.getExpenseId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("expenseEntity not found, expenseId: [%s]", expenseDTO.getExpenseId())));

        existingExpense.setName(expenseDTO.getName());
        existingExpense.setPrice(expenseDTO.getPrice());
        existingExpense.setCategory(CategoryEntity.builder()
                        .categoryId(categoryDTO.getCategoryId())
                        .name(categoryDTO.getName())
                .build());
        existingExpense.setEarner(EarnerEntity.builder()
                .earnerId(earnerDTO.getEarnerId())
                .name(earnerDTO.getName())
                .surname(earnerDTO.getSurname())
                .email(earnerDTO.getEmail())
                .build());


        expenseRepository.save(existingExpense);

        return "redirect:/expense";
    }

    @DeleteMapping("/delete/{expenseId}")
    public String deleteExpense(@PathVariable Integer expenseId) {
        expenseRepository.deleteById(expenseId);

        return "redirect:/expense";
    }

}

