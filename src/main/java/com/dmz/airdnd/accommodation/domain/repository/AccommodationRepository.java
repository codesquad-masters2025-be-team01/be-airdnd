package com.dmz.airdnd.accommodation.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmz.airdnd.accommodation.domain.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
