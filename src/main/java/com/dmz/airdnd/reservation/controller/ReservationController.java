package com.dmz.airdnd.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmz.airdnd.common.aop.RoleCheck;
import com.dmz.airdnd.common.dto.ApiResponse;
import com.dmz.airdnd.reservation.dto.request.ReservationRequest;
import com.dmz.airdnd.reservation.dto.response.ReservationResponse;
import com.dmz.airdnd.reservation.service.ReservationService;
import com.dmz.airdnd.user.domain.Role;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class ReservationController {

	private final ReservationService reservationService;

	@PostMapping
	@RoleCheck(Role.USER)
	public ResponseEntity<ApiResponse<ReservationResponse>> booking(
		@Valid @RequestBody ReservationRequest reservationRequest) {
		ReservationResponse response = reservationService.booking(reservationRequest);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success(response));
	}
}

