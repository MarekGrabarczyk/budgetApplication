package com.personal_budget.infrastucture.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "earner")
public class EarnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "earner_id")
    private Integer earnerId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "earner")
    private Set<EarningEntity> earningSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "earner")
    private Set<ExpenseEntity> expenseSet;
}
