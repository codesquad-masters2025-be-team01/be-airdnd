package com.dmz.airdnd.fixture;

import java.sql.Timestamp;
import java.util.List;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.accommodation.domain.Address;
import com.dmz.airdnd.accommodation.util.GeometryFactory;

public class TestAccommodationFactory {
	public static Accommodation createTestAccommodation(Address address) {
		return Accommodation.builder()
			.address(address)
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

	public static Accommodation createTestAccommodation(Long id, Address address) {
		return Accommodation.builder()
			.id(id)
			.address(address)
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

	public static List<Accommodation> createTestAccommodationList() {
		return List.of(
			Accommodation.builder()
				.address(Address.builder()
					.country("KR")
					.baseAddress("서울특별시 강남구")
					.location(GeometryFactory.createPoint(37.4966645, 127.0629804))
					.build())
				.name("서울 시내 모던룸1")
				.description("조용하고 따뜻한 다락방")
				.pricePerDay(55000)
				.currency("KRW")
				.maxGuests(2)
				.bedCount(1)
				.bedroomCount(1)
				.bathroomCount(1)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("서울특별시 강남구")
					.country("KR")
					.location(GeometryFactory.createPoint(37.4966646, 127.0629805))
					.build())
				.name("서울 시내 모던룸2")
				.description("지하철과 가까운 깔끔한 원룸")
				.pricePerDay(73000)
				.currency("KRW")
				.maxGuests(2)
				.bedCount(1)
				.bedroomCount(1)
				.bathroomCount(1)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("서울특별시 강남구")
					.country("KR")
					.location(GeometryFactory.createPoint(37.4966644, 127.0629806))
					.build())
				.name("서울 시내 모던룸3")
				.description("지하철과 가까운 깔끔한 원룸")
				.pricePerDay(7000)
				.currency("KRW")
				.maxGuests(2)
				.bedCount(1)
				.bedroomCount(1)
				.bathroomCount(1)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("서울특별시 강남구")
					.country("KR")
					.location(GeometryFactory.createPoint(37.4966636, 127.0629805))
					.build())
				.name("서울 시내 모던룸4")
				.description("지하철과 가까운 깔끔한 원룸")
				.pricePerDay(730000)
				.currency("KRW")
				.maxGuests(2)
				.bedCount(1)
				.bedroomCount(1)
				.bathroomCount(1)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("서울특별시 강남구")
					.country("KR")
					.location(GeometryFactory.createPoint(37.4966626, 127.0629805))
					.build())
				.name("서울 시내 모던룸5")
				.description("지하철과 멉니다")
				.pricePerDay(44000)
				.currency("KRW")
				.maxGuests(2)
				.bedCount(1)
				.bedroomCount(1)
				.bathroomCount(1)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("서울특별시 강남구")
					.country("KR")
					.location(GeometryFactory.createPoint(37.4966646, 127.0629804))
					.build())
				.name("서울 시내 모던룸6")
				.description("지하철 버스 모두 가까운 깔끔한 원룸")
				.pricePerDay(13000)
				.currency("KRW")
				.maxGuests(2)
				.bedCount(1)
				.bedroomCount(1)
				.bathroomCount(1)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("강원도 강릉시")
					.country("KR")
					.location(GeometryFactory.createPoint(35.1719465, 129.1741038))
					.build())
				.name("강릉 오션뷰 하우스")
				.description("바다가 보이는 테라스가 특징인 숙소")
				.pricePerDay(98000)
				.currency("KRW")
				.maxGuests(4)
				.bedCount(2)
				.bedroomCount(2)
				.bathroomCount(2)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("경상북도 경주시")
					.country("KR")
					.location(GeometryFactory.createPoint(35.8447433, 129.206998))
					.build())
				.name("경주 전통 한옥")
				.description("고즈넉한 분위기의 한옥 숙소")
				.pricePerDay(89000)
				.currency("KRW")
				.maxGuests(3)
				.bedCount(2)
				.bedroomCount(2)
				.bathroomCount(1)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("제주특별자치도 서귀포시")
					.country("KR")
					.location(GeometryFactory.createPoint(33.2532177, 126.5609945))
					.build())
				.name("제주 중문 풀빌라")
				.description("야외 수영장과 정원이 있는 풀빌라")
				.pricePerDay(210000)
				.currency("KRW")
				.maxGuests(6)
				.bedCount(3)
				.bedroomCount(3)
				.bathroomCount(2)
				.createdAt(now())
				.build(),

			Accommodation.builder()
				.address(Address.builder()
					.baseAddress("부산광역시 수영구")
					.country("KR")
					.location(GeometryFactory.createPoint(35.1610936, 129.1201036))
					.build())
				.name("부산 광안리 룸")
				.description("광안대교 야경이 멋진 숙소")
				.pricePerDay(67000)
				.currency("KRW")
				.maxGuests(2)
				.bedCount(1)
				.bedroomCount(1)
				.bathroomCount(1)
				.createdAt(now())
				.build()
		);
	}

	private static Timestamp now() {
		return new Timestamp(System.currentTimeMillis());
	}
}
