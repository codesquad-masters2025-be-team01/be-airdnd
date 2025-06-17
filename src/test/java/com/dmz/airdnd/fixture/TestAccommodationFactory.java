package com.dmz.airdnd.fixture;

import java.sql.Timestamp;

import com.dmz.airdnd.accommodation.domain.Accommodation;

public class TestAccommodationFactory {
	public static Accommodation createTestAccommodation() {
		return Accommodation.builder()
			.address(TestAddressFactory.createTestAddress(1L))
			.name("한라산뷰다락룸개인실")
			.description("건물이 목조주택이라 방이 아늑한 느낌입니다.")
			.pricePerDay(63900)
			.currency("KRW")
			.maxGuests(4)
			.bedCount(3)
			.bedroomCount(3)
			.bathroomCount(2)
			.createdAt(new Timestamp(System.currentTimeMillis()))
			.build();
	}

	public static Accommodation createTestAccommodation(Long id) {
		return Accommodation.builder()
			.id(id)
			.address(TestAddressFactory.createTestAddress(1L))
			.name("한라산뷰다락룸개인실")
			.description("건물이 목조주택이라 방이 아늑한 느낌입니다.")
			.pricePerDay(63900)
			.currency("KRW")
			.maxGuests(4)
			.bedCount(3)
			.bedroomCount(3)
			.bathroomCount(2)
			.createdAt(new Timestamp(System.currentTimeMillis()))
			.build();
	}
}
