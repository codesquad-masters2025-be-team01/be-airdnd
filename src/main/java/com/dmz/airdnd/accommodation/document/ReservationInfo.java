package com.dmz.airdnd.accommodation.document;

import java.time.LocalDate;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationInfo {
	@Field(type = FieldType.Date, format = DateFormat.date)
	private LocalDate checkIn;

	@Field(type = FieldType.Date, format = DateFormat.date)
	private LocalDate checkOut;
}
