package com.personal_budget.controller;

import com.personal_budget.infrastucture.database.entity.CategoryEntity;
import com.personal_budget.infrastucture.database.entity.EarnerEntity;
import com.personal_budget.infrastucture.database.repository.CategoryJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryJpaRepository categoryRepository;

    @GetMapping
    public String categoriesList(Model model) {
        List<CategoryEntity> allCategories = categoryRepository.findAll();

        model.addAttribute("categories", allCategories);
        model.addAttribute("categoryDTO", new CategoryDTO());

        return "category";
    }


    @PostMapping("/add")
    public String addCategory(
            @Valid @ModelAttribute("categoryDTO") CategoryDTO categoryDTO
    ) {
        CategoryEntity newCategory = CategoryEntity.builder()
                .name(categoryDTO.getName())
                .build();
        categoryRepository.save(newCategory);

        return "redirect:/category";
    }

    @PutMapping("/update")
    public String updateCategory(
            @Valid @ModelAttribute("categoryDTO") CategoryDTO categoryDTO
    ) {
        CategoryEntity existingCategory = categoryRepository.findById(categoryDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("CategoryEntity not found, categoryId: [%s]", categoryDTO.getCategoryId())));

        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);

        return "redirect:/category";
    }

    @DeleteMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId) {
        categoryRepository.deleteById(categoryId);

        return "redirect:/category";
    }
}

