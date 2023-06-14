package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
