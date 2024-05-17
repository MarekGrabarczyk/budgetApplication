package com.personal_budget.controller;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarnerDTO {

    private Integer earnerId;

    private String name;

    private String surname;

    private String email;
}
