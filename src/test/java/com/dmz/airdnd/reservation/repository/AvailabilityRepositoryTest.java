package com.dmz.airdnd.reservation.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dmz.airdnd.AbstractContainerBase;
import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.accommodation.domain.Address;
import com.dmz.airdnd.accommodation.repository.AccommodationRepository;
import com.dmz.airdnd.accommodation.repository.AddressRepository;
import com.dmz.airdnd.fixture.TestAccommodationFactory;
import com.dmz.airdnd.fixture.TestAddressFactory;
import com.dmz.airdnd.fixture.TestAvailabilityFactory;
import com.dmz.airdnd.fixture.TestReservationFactory;
import com.dmz.airdnd.fixture.TestUserFactory;
import com.dmz.airdnd.reservation.domain.Availability;
import com.dmz.airdnd.reservation.domain.Reservation;
import com.dmz.airdnd.user.domain.User;
import com.dmz.airdnd.user.repository.UserRepository;

@DataJpaTest
class AvailabilityRepositoryTest extends AbstractContainerBase {

	@Autowired
	private AvailabilityRepository availabilityRepository;

	@Autowired
	private AccommodationRepository accommodationRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	private Accommodation accommodation;

	private Reservation reservation;

	@BeforeEach
	void setup() {
		User guest = userRepository.save(TestUserFactory.createTestUser());
		Address address = addressRepository.save(TestAddressFactory.createTestAddress());
		accommodation = accommodationRepository.save(TestAccommodationFactory.createTestAccommodation(address));
		reservation = reservationRepository.save(TestReservationFactory.createTestReservation(guest, accommodation));
	}

	@Test
	@DisplayName("예약 기간 동안 각 날짜에 대한 Availability가 모두 저장된다")
	void success_saveAllAvailability() {
		//given
		List<Availability> availabilities = TestAvailabilityFactory.createTestAvailabilities(accommodation,
			reservation);
		//when
		availabilityRepository.saveAll(availabilities);
		//then
		List<Availability> newAvailabilities = availabilityRepository.findAll();
		assertThat(newAvailabilities)
			.extracting(Availability::getDate)
			.containsExactlyInAnyOrder(
				reservation.getCheckInDate(),
				reservation.getCheckOutDate().minusDays(1)
			);
	}
}
