package com.escl.mapper;

import com.escl.dto.ContactRequestDTO;
import com.escl.dto.ContactResponseDTO;
import com.escl.model.Contact;

public class ContactMapper {

    // RequestDTO -> Entity
    public static Contact toEntity(ContactRequestDTO dto) {

        Contact contact = new Contact();

        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setCompany(dto.getCompany());
        contact.setPhone(dto.getPhone());
        contact.setMatchInterest(dto.getMatchInterest());
        contact.setMessage(dto.getMessage());

        return contact;
    }

    // Entity -> ResponseDTO
    public static ContactResponseDTO toDTO(Contact contact) {

        ContactResponseDTO dto = new ContactResponseDTO();

        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        dto.setCompany(contact.getCompany());
        dto.setPhone(contact.getPhone());
        dto.setMatchInterest(contact.getMatchInterest());
        dto.setMessage(contact.getMessage());

        return dto;
    }
}