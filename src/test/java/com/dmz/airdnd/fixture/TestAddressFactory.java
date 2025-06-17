package com.dmz.airdnd.fixture;

import com.dmz.airdnd.accommodation.domain.Address;

public class TestAddressFactory {
	public static Address createTestAddress() {
		return Address.builder()
			.country("대한민국")
			.city("제주도")
			.state("서울특별시")
			.postalCode("12345")
			.addressLine1("강남구 테헤란로 123")
			.addressLine2("ABC 오피스텔 101호")
			.location("37.123456,127.123456")
			.build();
	}

	public static Address createTestAddress(Long id) {
		return Address.builder()
			.id(id)
			.country("대한민국")
			.city("제주도")
			.state("서울특별시")
			.postalCode("12345")
			.addressLine1("강남구 테헤란로 123")
			.addressLine2("ABC 오피스텔 101호")
			.location("37.123456,127.123456")
			.build();
	}
}
