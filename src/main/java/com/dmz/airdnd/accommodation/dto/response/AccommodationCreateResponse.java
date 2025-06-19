package com.dmz.airdnd.accommodation.dto.response;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.accommodation.domain.Address;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationCreateResponse {
	private final Long id;
	private final String address;
	private final String name;
	private final String description;
	private final Long pricePerDay;
	private final String currency;
	private final Integer maxGuests;
	private final Integer bedCount;
	private final Integer bedroomCount;
	private final Integer bathroomCount;
	private final Timestamp createdAt;
	private final Timestamp updatedAt;

	public static AccommodationCreateResponse fromEntity(Accommodation accommodation) {
		return AccommodationCreateResponse.builder()
			.id(accommodation.getId())
			.address(formatFullAddress(accommodation.getAddress()))
			.name(accommodation.getName())
			.description(accommodation.getDescription())
			.pricePerDay(accommodation.getPricePerDay())
			.currency(accommodation.getCurrency())
			.maxGuests(accommodation.getMaxGuests())
			.bedCount(accommodation.getBedCount())
			.bedroomCount(accommodation.getBedroomCount())
			.bathroomCount(accommodation.getBathroomCount())
			.createdAt(accommodation.getCreatedAt())
			.updatedAt(accommodation.getUpdatedAt())
			.build();
	}

	private static String formatFullAddress(Address address) {
		String base = address.getBaseAddress();
		String detail = address.getDetailedAddress();
		if (detail != null && !detail.isEmpty()) {
			return base + " " + detail;
		}
		return base;
	}
}
