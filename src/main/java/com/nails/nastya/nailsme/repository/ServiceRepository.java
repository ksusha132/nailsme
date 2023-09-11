package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.PriceMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<PriceMenu, Integer> {
}
