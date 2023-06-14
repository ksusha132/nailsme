package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
