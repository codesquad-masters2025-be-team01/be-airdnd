package com.dmz.airdnd.fixture;

import java.time.LocalDate;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.reservation.domain.Availability;
import com.dmz.airdnd.reservation.domain.Reservation;

public class TestAvailabilityFactory {
	public static Availability createTestAvailability(Accommodation accommodation, Reservation reservation,
		LocalDate date) {
		return Availability.builder()
			.date(date)
			.accommodation(accommodation)
			.reservation(reservation)
			.build();
	}
}
