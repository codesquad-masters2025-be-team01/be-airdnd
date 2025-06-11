package com.dmz.airdnd.user.dto.request.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserRequest {
	@NotEmpty
	@Size(min = 5, max = 25)
	private String loginId;

	@NotEmpty
	@Size(min = 5, max = 25)
	private String password;

	@NotEmpty
	@Email
	@Size(min = 5, max = 25)
	private String email;

	@NotEmpty
	@Size(min = 8, max = 25)
	private String phone;
}
