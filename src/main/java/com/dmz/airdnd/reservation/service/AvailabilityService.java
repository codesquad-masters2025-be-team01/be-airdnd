package com.dmz.airdnd.reservation.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.reservation.domain.Availability;
import com.dmz.airdnd.reservation.domain.Reservation;
import com.dmz.airdnd.reservation.repository.AvailabilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

	private final AvailabilityRepository availabilityRepository;

	public void createAvailability(Accommodation accommodation, Reservation reservation) {
		List<Availability> availabilities = generateAvailabilities(accommodation, reservation);
		availabilityRepository.saveAll(availabilities);
	}

	private List<Availability> generateAvailabilities(Accommodation accommodation, Reservation reservation) {
		List<Availability> availabilities = new ArrayList<>();
		for (LocalDate date = reservation.getCheckInDate(); date.isBefore(
			reservation.getCheckOutDate()); date = date.plusDays(1)) {
			availabilities.add(Availability.builder()
				.accommodation(accommodation)
				.reservation(reservation)
				.date(date)
				.build());
		}
		return availabilities;
	}
}
