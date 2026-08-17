package com.escl.service;

import com.escl.dto.ContactRequestDTO;
import com.escl.dto.ContactResponseDTO;
import com.escl.exception.ResourceNotFoundException;
import com.escl.mapper.ContactMapper;
import com.escl.model.Contact;
import com.escl.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repo;

    // Save Contact
    public ContactResponseDTO saveContact(ContactRequestDTO requestDTO) {

        Contact contact = ContactMapper.toEntity(requestDTO);

        Contact savedContact = repo.save(contact);

        return ContactMapper.toDTO(savedContact);
    }

    // Get All Contacts
    public List<ContactResponseDTO> getAllContacts() {

        return repo.findAll()
                .stream()
                .map(ContactMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get Contact By id
    public ContactResponseDTO getContactById(Long id) {

        Contact contact = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contact Not Found with ID : " + id));

        return ContactMapper.toDTO(contact);
    }

    // Update Contact
    public ContactResponseDTO updateContact(Long id,
                                            ContactRequestDTO requestDTO) {

        Contact contact = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contact Not Found with ID : " + id));

        contact.setName(requestDTO.getName());
        contact.setEmail(requestDTO.getEmail());
        contact.setCompany(requestDTO.getCompany());
        contact.setPhone(requestDTO.getPhone());
        contact.setMatchInterest(requestDTO.getMatchInterest());
        contact.setMessage(requestDTO.getMessage());

        Contact updatedContact = repo.save(contact);

        return ContactMapper.toDTO(updatedContact);
    }

    // Delete Contact
    public String deleteContact(Long id) {

        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Contact Not Found with ID : " + id);
        }

        repo.deleteById(id);

        return "Contact Deleted Successfully";
    }
}