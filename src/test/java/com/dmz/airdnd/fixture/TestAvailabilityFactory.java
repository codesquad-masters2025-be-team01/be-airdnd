package com.dmz.airdnd.fixture;

import java.util.Date;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.reservation.domain.Availability;
import com.dmz.airdnd.reservation.domain.Reservation;

public class TestAvailabilityFactory {
	public static Availability createTestAvailability(Accommodation accommodation, Reservation reservation) {
		return Availability.builder()
			.date(new Date())
			.accommodation(accommodation)
			.reservation(reservation)
			.build();
	}
}
