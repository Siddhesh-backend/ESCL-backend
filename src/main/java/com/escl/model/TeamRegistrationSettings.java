
package com.escl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "team_registration_settings")
@Getter
@Setter
@NoArgsConstructor
public class TeamRegistrationSettings {

    @Id
    private Long id = 1L;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal entryFee = new BigDecimal("2500.00");
}
