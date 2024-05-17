package com.personal_budget.controller;

import com.personal_budget.infrastucture.database.entity.CategoryEntity;
import com.personal_budget.infrastucture.database.entity.EarnerEntity;
import com.personal_budget.infrastucture.database.entity.EarningEntity;
import com.personal_budget.infrastucture.database.entity.ExpenseEntity;
import com.personal_budget.infrastucture.database.repository.EarnerJpaRepository;
import com.personal_budget.infrastucture.database.repository.EarningJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/earning")
@AllArgsConstructor
public class EarningController {

    private EarningJpaRepository earningRepository;
    private EarnerJpaRepository earnerRepository;


    @GetMapping
    public String expensesList(Model model) {
        List<EarningEntity> allEarnings = earningRepository.findAll();
        List<EarnerEntity> allEarners = earnerRepository.findAll();


        model.addAttribute("earnings", allEarnings);
        model.addAttribute("earningDTO", new EarningDTO());
        model.addAttribute("earnerDTO", new EarnerDTO());
        model.addAttribute("earners", allEarners);



        return "earning";
    }

    @PostMapping("/add")
    public String addEarning(
            @ModelAttribute("earningDTO") EarningDTO earningDTO,
            @ModelAttribute("earnerDTO") EarnerDTO earnerDTO

    ) {


        EarningEntity newEarning = EarningEntity.builder()
                .name(earningDTO.getName())
                .salary(earningDTO.getSalary())
                .earner(
                        EarnerEntity.builder()
                                .earnerId(earnerDTO.getEarnerId())
                                .name(earnerDTO.getName())
                                .surname(earnerDTO.getSurname())
                                .email(earnerDTO.getEmail())
                                .build()
                )
                .build();


        earningRepository.save(newEarning);

        return "redirect:/earning";
    }
    @PutMapping("/update")
    public String updateEarner(
            @ModelAttribute("earningDTO") EarningDTO earningDTO,
            @ModelAttribute("earnerDTO") EarnerDTO earnerDTO,
            @ModelAttribute("categoryDTO") CategoryDTO categoryDTO
    ) {
        EarningEntity existingEarning = earningRepository.findById(earningDTO.getEarningId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("earningEntity not found, expenseId: [%s]", earningDTO.getEarningId())));

        existingEarning.setName(earningDTO.getName());
        existingEarning.setSalary(earningDTO.getSalary());
        existingEarning.setEarner(EarnerEntity.builder()
                .earnerId(earnerDTO.getEarnerId())
                .name(earnerDTO.getName())
                .surname(earnerDTO.getSurname())
                .email(earnerDTO.getEmail())
                .build());


        earningRepository.save(existingEarning);

        return "redirect:/earning";
    }

    @DeleteMapping("/delete/{earningId}")
    public String deleteEarning(@PathVariable Integer earningId) {
        earningRepository.deleteById(earningId);

        return "redirect:/earning";
    }

}
