package com.example.springbootsintro.repository;

import com.example.springbootsintro.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, String> {
}
