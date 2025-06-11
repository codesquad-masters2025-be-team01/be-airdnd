package com.dmz.airdnd.common.auth;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dmz.airdnd.user.domain.Role;
import com.dmz.airdnd.user.domain.User;
import com.dmz.airdnd.user.dto.request.response.UserRequest;
import com.dmz.airdnd.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	@InjectMocks
	private AuthService authService;

	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("중복된 유저가 없으면 회원가입을 성공한다.")
	void testSignup() {
		//given
		User user = createTestUser();
		UserRequest userRequest = createUserRequestFrom(user);
		when(userRepository.existsByLoginId(anyString())).thenReturn(false);
		when(userRepository.existsByEmail(anyString())).thenReturn(false);
		when(userRepository.existsByPhone(anyString())).thenReturn(false);
		when(userRepository.save(any())).thenReturn(user);
		//when
		User newUser = authService.signup(userRequest);
		//then
		verify(userRepository).existsByLoginId(user.getLoginId());
		verify(userRepository).existsByEmail(user.getEmail());
		verify(userRepository).existsByPhone(user.getPhone());
		assertThat(newUser.getLoginId()).isEqualTo(user.getLoginId());
		assertThat(newUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(newUser.getPhone()).isEqualTo(user.getPhone());
		assertThat(newUser.getRole()).isEqualTo(Role.USER);
	}

	private User createTestUser() {
		return User.builder()
			.id(1L)
			.loginId("testUser")
			.password("password123")
			.email("test@test.com")
			.role(Role.USER)
			.phone("010-1234-5678")
			.build();
	}

	@ParameterizedTest
	@MethodSource("provideDuplicateRequests")
	@DisplayName("중복된 필드가 있을 경우 회원가입에 실패한다.")
	void duplicateFieldTest(UserRequest request, String duplicateField) {
		//given
		switch (duplicateField) {
			case "loginId" -> {
				when(userRepository.existsByLoginId(request.getLoginId())).thenReturn(true);
			}
			case "email" -> {
				when(userRepository.existsByLoginId(request.getLoginId())).thenReturn(false);
				when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);
			}
			case "phone" -> {
				when(userRepository.existsByLoginId(request.getLoginId())).thenReturn(false);
				when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
				when(userRepository.existsByPhone(request.getPhone())).thenReturn(true);
			}
		}
		//when + then
		switch (duplicateField) {
			case "loginId" -> assertDuplicateError(() -> authService.signup(request), "이미 존재하는 로그인 아이디입니다.");
			case "email" -> assertDuplicateError(() -> authService.signup(request), "이미 존재하는 이메일입니다.");
			case "phone" -> assertDuplicateError(() -> authService.signup(request), "이미 존재하는 전화번호입니다.");
		}
	}

	private void assertDuplicateError(Runnable executable, String expectedMessage) {
		assertThatThrownBy(executable::run)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(expectedMessage);
	}

	private static Stream<Arguments> provideDuplicateRequests() {
		return Stream.of(
			Arguments.of(new UserRequest("test123", "pw", "new@example.com", "010-9999-8888"), "loginId"), // loginId 중복
			Arguments.of(new UserRequest("newId", "pw", "test@example.com", "010-9999-8888"), "email"),   // email 중복
			Arguments.of(new UserRequest("newId", "pw", "new@example.com", "010-1234-5678"), "phone")    // phone 중복
		);
	}

	private UserRequest createUserRequestFrom(User user) {
		return UserRequest.builder()
			.loginId(user.getLoginId())
			.password(user.getPassword())
			.email(user.getEmail())
			.phone(user.getPhone())
			.build();
	}
}
