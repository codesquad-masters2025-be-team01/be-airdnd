package com.dmz.airdnd.accommodation.document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.geo.Point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(indexName = "accommodations")
public class AccommodationDocument {

	@Id
	private final String id;

	@Field(type = FieldType.Text)
	private final String name;

	@Field(type = FieldType.Text)
	private final String description;

	@Field(type = FieldType.Long)
	private final Long pricePerDay;

	@Field(type = FieldType.Keyword)
	private final String currency;

	@Field(type = FieldType.Integer)
	private final Integer maxGuests;

	@Field(type = FieldType.Integer)
	private final Integer bedCount;

	@Field(type = FieldType.Integer)
	private final Integer bedroomCount;

	@Field(type = FieldType.Integer)
	private final Integer bathroomCount;

	@Field(type = FieldType.Date)
	private final Instant createdAt;

	@Field(type = FieldType.Date)
	private final Instant updatedAt;

	@GeoPointField
	private final Point location;

	@Field(type = FieldType.Keyword)
	private final String country;

	@Field(type = FieldType.Text)
	private final String baseAddress;

	@Field(type = FieldType.Text)
	private final String detailedAddress;

	@Field(type = FieldType.Keyword)
	private final List<String> labels;

	@Field(type = FieldType.Date, format = DateFormat.date)
	private final List<LocalDate> availableDates;

	@Field(type = FieldType.Nested)
	private final List<ReservationInfo> reservations;
}
