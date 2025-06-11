package com.dmz.airdnd.common.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmz.airdnd.common.dto.ApiResponse;
import com.dmz.airdnd.user.dto.request.response.UserRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<Void>> signup(UserRequest userRequest) {
		authService.signup(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(null));
	}
}
