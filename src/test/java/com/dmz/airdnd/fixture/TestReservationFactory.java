package com.dmz.airdnd.fixture;

import java.time.LocalDateTime;
import java.util.Date;

import com.dmz.airdnd.reservation.domain.Reservation;
import com.dmz.airdnd.reservation.domain.ReservationStatus;

public class TestReservationFactory {
	public static Reservation createTestReservation() {
		return Reservation.builder()
			.guest(TestUserFactory.createTestUser(1L))
			.accommodation(TestAccommodationFactory.createTestAccommodation(1L))
			.checkInDate(new Date())
			.checkOutDate(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24))  // 1일 후
			.numberOfGuests(10)
			.totalPrice(200000)
			.status(ReservationStatus.PENDING)
			.timezone("Asia/Seoul")
			.currency("KRW")
			.createdAt(LocalDateTime.now())
			.build();
	}
}
