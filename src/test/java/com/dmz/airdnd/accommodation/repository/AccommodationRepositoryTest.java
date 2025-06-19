package com.dmz.airdnd.accommodation.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.dmz.airdnd.AbstractContainerBase;
import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.accommodation.dto.FilterCondition;
import com.dmz.airdnd.common.config.QuerydslConfig;
import com.dmz.airdnd.fixture.TestAccommodationFactory;

@DataJpaTest
@Import(QuerydslConfig.class)
class AccommodationRepositoryTest extends AbstractContainerBase {

	@Autowired
	private AccommodationRepository accommodationRepository;

	@Test
	@DisplayName("필터링 조건에 따라 숙소를 조회할 수 있다.")
	void success_findFilteredAccommodations() {
		// given
		List<Accommodation> accommodationList = TestAccommodationFactory.createTestAccommodationList();
		accommodationList = accommodationRepository.saveAll(accommodationList);

		FilterCondition filterCondition = FilterCondition.builder()
			.longitude(37.4966645)
			.latitude(127.0629804)
			.minPrice(10000)
			.maxPrice(100000)
			.maxGuests(1)
			.requestedDates(List.of(
				LocalDate.of(2026, 1, 1),
				LocalDate.of(2026, 1, 2),
				LocalDate.of(2026, 1, 3)
			))
			.build();
		// when
		Page<Accommodation> accommodations = accommodationRepository.findFilteredAccommodations(PageRequest.of(0, 3),
			filterCondition);

		// then
		for (Accommodation accommodation : accommodations.getContent()) {
			System.out.println(accommodation.getName());
		}
		assertThat(accommodations.getTotalElements()).isEqualTo(4);
	}
}
