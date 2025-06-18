package com.dmz.airdnd.accommodation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.accommodation.dto.request.AccommodationSearchRequest;

public interface AccommodationRepositoryCustom {
	Page<Accommodation> findFilteredAccommodations(Pageable pageable, AccommodationSearchRequest request);
}
