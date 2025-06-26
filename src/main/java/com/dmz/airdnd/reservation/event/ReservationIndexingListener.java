package com.dmz.airdnd.reservation.event;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.dmz.airdnd.accommodation.document.AccommodationDocument;
import com.dmz.airdnd.accommodation.document.ReservationInfo;
import com.dmz.airdnd.reservation.domain.Reservation;
import com.dmz.airdnd.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationIndexingListener {
	private final ReservationRepository reservationRepository;
	private final ElasticsearchRepository<AccommodationDocument, String> elasticsearchRepository;

	@EventListener
	public void handle(ReservationCreatedEvent event) {
		Reservation reservation = reservationRepository.findById(event.getReservationId()).orElseThrow();
		String accommodationId = reservation.getAccommodation().getId().toString();

		AccommodationDocument document = elasticsearchRepository.findById(accommodationId).orElseThrow();

		List<ReservationInfo> infos = reservationRepository
			.findByAccommodationId(reservation.getAccommodation().getId()).stream()
			.map(existingReservation -> new ReservationInfo(
				existingReservation.getCheckInDate(),
				existingReservation.getCheckOutDate()))
			.toList();

		AccommodationDocument updated = document.toBuilder()
			.reservations(infos)
			.build();
		elasticsearchRepository.save(updated);
	}
}
