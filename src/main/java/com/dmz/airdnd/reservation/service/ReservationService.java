package com.dmz.airdnd.reservation.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.airdnd.accommodation.domain.Accommodation;
import com.dmz.airdnd.accommodation.service.AccommodationService;
import com.dmz.airdnd.common.aop.RoleCheck;
import com.dmz.airdnd.common.auth.UserContext;
import com.dmz.airdnd.common.auth.dto.UserInfo;
import com.dmz.airdnd.common.exception.DuplicateReservationException;
import com.dmz.airdnd.common.exception.ErrorCode;
import com.dmz.airdnd.reservation.domain.Reservation;
import com.dmz.airdnd.reservation.dto.request.ReservationRequest;
import com.dmz.airdnd.reservation.dto.response.ReservationResponse;
import com.dmz.airdnd.reservation.mapper.ReservationMapper;
import com.dmz.airdnd.reservation.repository.ReservationRepository;
import com.dmz.airdnd.user.domain.Role;
import com.dmz.airdnd.user.domain.User;
import com.dmz.airdnd.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;

	private final AccommodationService accommodationService;

	private final AvailabilityService availabilityService;

	private final UserService userService;

	@Transactional
	@RoleCheck(Role.USER)
	public ReservationResponse booking(Long accommodationId, ReservationRequest reservationRequest) {
		Accommodation accommodation = accommodationService.getAccommodationById(accommodationId);

		User guest = getCurrentUser();

		Reservation reservation = ReservationMapper.toEntity(reservationRequest, guest, accommodation);

		try {
			Reservation saved = reservationRepository.save(reservation);
			availabilityService.saveReservationDates(accommodation, saved);
			return ReservationMapper.toResponse(saved, accommodation);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateReservationException(ErrorCode.DUPLICATE_RESERVATION);
		}
	}

	private User getCurrentUser() {
		UserInfo currentUser = UserContext.get();
		return userService.getUserFindById(currentUser.getId());
	}
}
