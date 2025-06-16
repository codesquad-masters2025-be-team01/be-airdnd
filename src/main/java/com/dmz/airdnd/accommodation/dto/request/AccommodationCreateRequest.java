package com.dmz.airdnd.accommodation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationCreateRequest {

	@NotBlank(message = "숙소 이름은 필수 입력 항목입니다.")
	@Size(min = 1, max = 50, message = "숙소 이름은 1~50자 이내여야 합니다.")
	private String name;

	private String description;

	@NotNull(message = "하루 가격은 필수 입력 항목입니다.")
	@PositiveOrZero(message = "하루 가격은 0 이상이어야 합니다.")
	private Long pricePerDay;

	@NotBlank(message = "통화 단위는 필수 입력 항목입니다.")
	@Pattern(
		regexp = "^[A-Z]{3}$",
		message = "통화 단위는 3자리 영문 대문자여야 합니다.")
	private String currency;

	@NotNull(message = "최대 인원 수는 필수 입력 항목입니다.")
	@Positive(message = "최대 인원 수는 1 이상이어야 합니다.")
	private Integer maxGuests;

	@NotNull(message = "침대 수는 필수 입력 항목입니다.")
	@PositiveOrZero(message = "침대 수는 0 이상이어야 합니다.")
	private Integer bedCount;

	@NotNull(message = "침실 수는 필수 입력 항목입니다.")
	@PositiveOrZero(message = "침실 수는 0 이상이어야 합니다.")
	private Integer bedroomCount;

	@NotNull(message = "욕실 수는 필수 입력 항목입니다.")
	@PositiveOrZero(message = "욕실 수는 0 이상이어야 합니다.")
	private Integer bathroomCount;

	@NotNull(message = "주소 ID는 필수 입력 항목입니다.")
	private Long addressId;
}
