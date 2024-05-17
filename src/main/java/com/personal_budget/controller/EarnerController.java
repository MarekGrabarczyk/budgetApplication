package com.personal_budget.controller;

import com.personal_budget.infrastucture.database.entity.EarnerEntity;
import com.personal_budget.infrastucture.database.repository.EarnerJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/earner")
@AllArgsConstructor
public class EarnerController {

    private EarnerJpaRepository earnerRepository;

    @GetMapping
    public String earnersList(Model model) {
        List<EarnerEntity> allEarners = earnerRepository.findAll();

        model.addAttribute("earners", allEarners);
        model.addAttribute("earnerDTO", new EarnerDTO());

        return "earner";
    }

    @GetMapping("/show/{earnerId}")
    public String showEarnerDetails(@PathVariable Integer earnerId, Model model) {
        EarnerEntity earnerDetails = earnerRepository.findById(earnerId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("EarnerEntity not found, earnerId: [%s]", earnerId)));

        model.addAttribute("earner", earnerDetails);

        return "earnerDetails";
    }

    @PostMapping("/add")
    public String addEarner(
            @Valid @ModelAttribute("earnerDTO") EarnerDTO earnerDTO
    ) {
        EarnerEntity newEarner = EarnerEntity.builder()
                .name(earnerDTO.getName())
                .surname(earnerDTO.getSurname())
                .email(earnerDTO.getEmail())
                .build();
        earnerRepository.save(newEarner);

        return "redirect:/earner";
    }
    @PutMapping("/update")
    public String updateEarner(
            @Valid @ModelAttribute("earnerDTO") EarnerDTO earnerDTO
    ) {
        EarnerEntity existingEarner = earnerRepository.findById(earnerDTO.getEarnerId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("EarnerEntity not found, earnerId: [%s]", earnerDTO.getEarnerId())));

        existingEarner.setName(earnerDTO.getName());
        existingEarner.setSurname(earnerDTO.getSurname());
        existingEarner.setEmail(earnerDTO.getEmail());
        earnerRepository.save(existingEarner);

        return "redirect:/earner";
    }

    @DeleteMapping("/delete/{earnerId}")
    public String deleteEarner(@PathVariable Integer earnerId, Model model) {

        try {
            earnerRepository.deleteById(earnerId);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Cannot delete earner with associated earnings or expenses.");
            return "error"; // Przekieruj na stronę z błędem
        }

        return "redirect:/earner";
    }

}
