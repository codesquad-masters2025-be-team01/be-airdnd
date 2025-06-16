package com.dmz.airdnd.reservation.domain;

import java.util.Date;

import com.dmz.airdnd.accommodation.domain.Accommodation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Availability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date date;

	@ManyToOne(optional = false)
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Accommodation accommodation;

	@ManyToOne(optional = false)
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;
}
