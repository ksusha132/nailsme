package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
