package com.dmz.airdnd.fixture;

import java.time.LocalDateTime;
import java.util.Date;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.reservation.domain.Reservation;
import com.dmz.airdnd.reservation.domain.ReservationStatus;
import com.dmz.airdnd.user.domain.User;

public class TestReservationFactory {
	public static Reservation createTestReservation(Accommodation accommodation, User guest) {
		return Reservation.builder()
			.guest(guest)
			.accommodation(accommodation)
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
