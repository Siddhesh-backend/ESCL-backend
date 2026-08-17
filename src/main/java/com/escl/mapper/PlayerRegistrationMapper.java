package com.escl.mapper;

import com.escl.dto.PlayerRegistrationRequestDTO;
import com.escl.dto.PlayerRegistrationResponseDTO;
import com.escl.model.PlayerRegistration;

public class PlayerRegistrationMapper {

    // RequestDTO -> Entity
    public static PlayerRegistration toEntity(PlayerRegistrationRequestDTO dto) {

        PlayerRegistration player = new PlayerRegistration();

        player.setFullName(dto.getFullName());
        player.setCollegeName(dto.getCollegeName());
        player.setBranch(dto.getBranch());
        player.setYear(dto.getYear());
        player.setDob(dto.getDob());
        player.setPosition(dto.getPosition());
        player.setExperience(dto.getExperience());
        player.setCricheroesUrl(dto.getCricheroesUrl());
        player.setBasePrice(dto.getBasePrice());
        player.setEmail(dto.getEmail());
        player.setPassword(dto.getPassword());
        player.setPaymentMethod(dto.getPaymentMethod());

        return player;
    }

    // Entity -> ResponseDTO
    public static PlayerRegistrationResponseDTO toDTO(PlayerRegistration player) {

        PlayerRegistrationResponseDTO dto =
                new PlayerRegistrationResponseDTO();

        dto.setId(player.getId());
        dto.setFullName(player.getFullName());
        dto.setCollegeName(player.getCollegeName());
        dto.setBranch(player.getBranch());
        dto.setYear(player.getYear());
        dto.setDob(player.getDob());
        dto.setPosition(player.getPosition());
        dto.setExperience(player.getExperience());
        dto.setCricheroesUrl(player.getCricheroesUrl());
        dto.setBasePrice(player.getBasePrice());
        dto.setEmail(player.getEmail());
        dto.setPaymentMethod(player.getPaymentMethod());

        dto.setRole(player.getRole());
        return dto;
    }
}