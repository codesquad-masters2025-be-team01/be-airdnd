package com.dmz.airdnd.reservation.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReservationRequest {

	private Long accommodationId;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;

	private int numberOfGuests;

	private String timezone;
}
