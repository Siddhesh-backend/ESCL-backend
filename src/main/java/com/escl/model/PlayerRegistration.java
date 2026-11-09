package com.escl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="player_registration")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank
    private String fullName;
    private String collegeName;
    private String branch;
    private String year;
    private String dob;
    private String position;
    private String experience;
    private String cricheroesUrl;
    private String email;
    private String password;
    private String paymentMethod;
    private Double basePrice;

    private String role;

    // Team Registration Fields
    private String collegeId;

    private Integer jerseyNumber;

    private String contactNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_registration_id")
    private TeamRegistration teamRegistration;

}
