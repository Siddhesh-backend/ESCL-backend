package com.escl.controller;

import com.escl.dto.ContactRequestDTO;
import com.escl.dto.ContactResponseDTO;
import com.escl.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Save Contact
    @PostMapping
    public ResponseEntity<ContactResponseDTO> saveContact(
            @Valid @RequestBody ContactRequestDTO requestDTO) {

        ContactResponseDTO responseDTO =
                contactService.saveContact(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Get All Contacts
    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts() {

        List<ContactResponseDTO> contacts =
                contactService.getAllContacts();

        return ResponseEntity.ok(contacts);
    }

    // Get Contact By ID
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(
            @PathVariable Long id) {

        ContactResponseDTO contact =
                contactService.getContactById(id);

        return ResponseEntity.ok(contact);
    }

    // Update Contact
    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @PathVariable Long id,
            @Valid @RequestBody ContactRequestDTO requestDTO) {

        ContactResponseDTO updatedContact =
                contactService.updateContact(id, requestDTO);

        return ResponseEntity.ok(updatedContact);
    }

    // Delete Contact
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(
            @PathVariable Long id) {

        String message =
                contactService.deleteContact(id);

        return ResponseEntity.ok(message);
    }
}
