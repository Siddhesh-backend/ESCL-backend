package com.escl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escl.model.Contact;

public interface ContactRepository
        extends JpaRepository<Contact, Long> {

}